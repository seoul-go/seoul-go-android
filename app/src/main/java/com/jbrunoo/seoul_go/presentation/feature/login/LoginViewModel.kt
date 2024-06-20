package com.jbrunoo.seoul_go.presentation.feature.login

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.jbrunoo.seoul_go.BuildConfig
import com.jbrunoo.seoul_go.common.Constants
import com.jbrunoo.seoul_go.domain.model.User
import com.jbrunoo.seoul_go.domain.useCase.auth.GetAuthUseCase
import com.jbrunoo.seoul_go.domain.useCase.auth.LoginUseCase
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    getAuthUseCase: GetAuthUseCase
) : ViewModel() {

    val auth: StateFlow<String> = getAuthUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Constants.SOCIAL_TYPE.UNAUTHENTICATED.type
    )

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun googleLogin(context: Context) {
        _isLoading.value = true
        val credentialManager = CredentialManager.create(context)
        viewModelScope.launch {
            try {
                val request = getGoogleCredentialRequest()
                val credential = credentialManager.getCredential(
                    request = request,
                    context = context,
                )
                handleGoogleSignIn(credential)
            } catch (e: GetCredentialException) {
                println(e.errorMessage)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun kakaoLogin(context: Context) {
        _isLoading.value = true
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Timber.d("카카오계정으로 로그인 실패")
            } else if (token != null) {
                Timber.d("카카오계정으로 로그인 성공")
            }
            _isLoading.value = false
        }
        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    Timber.d("카카오톡으로 로그인 실패")

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
                    handleKakaoSignIn()
                } else if (token != null) {
                    Timber.d("카카오톡으로 로그인 성공")
                    handleKakaoSignIn()
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
            handleKakaoSignIn()
        }
    }

    fun guestLogin() {
        _isLoading.value = true
        try {
            setAuthAndUserInfo(userInfo = null)
        } finally {
            _isLoading.value = false
        }
    }

    private fun getGoogleCredentialRequest(): GetCredentialRequest {
        return GetCredentialRequest.Builder()
            .addCredentialOption(getGoogleIdOption())
            .build()
    }

    private fun getGoogleIdOption(): GetGoogleIdOption {
        val googleClientId = BuildConfig.GOOGLE_CLIENT_ID
        return GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(googleClientId)
//            .setAutoSelectEnabled(true)
            .build()
    }

    private fun handleGoogleSignIn(result: GetCredentialResponse) {
        // Handle the successfully returned credential.

        when (val credential = result.credential) {
            // GoogleIdToken credential
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        // Use googleIdTokenCredential and extract id to validate and
                        // authenticate on your server.
                        val googleIdTokenCredential = GoogleIdTokenCredential
                            .createFrom(credential.data)
                        setAuthAndUserInfo(googleIdTokenCredential)
                    } catch (e: GoogleIdTokenParsingException) {
                        Timber.d("Received an invalid google id token response")
                    }
                } else {
                    // Catch any unrecognized custom credential type here.
                    Timber.d("Unexpected type of credential")
                }
            }
            else -> {
                Timber.d("Unexpected type of credential")
            }
        }
    }

    private fun handleKakaoSignIn() {
        UserApiClient.instance.me { user, err ->
            if (err != null) {
                Timber.d("사용자 정보 요청 실패")
            } else if (user != null) {
                Timber.d("사용자 정보 요청 성공")
                setAuthAndUserInfo(user)
            }
        }
    }

    private fun setAuthAndUserInfo(userInfo: Any?) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                when (userInfo) {
                    is GoogleIdTokenCredential -> {
                        val googleUser = User(
                            id = userInfo.id,
                            userName = userInfo.displayName,
                            userProfileUri = userInfo.profilePictureUri?.toString()
                        )
                        loginUseCase(Constants.SOCIAL_TYPE.GOOGLE, googleUser)
                    }

                    is com.kakao.sdk.user.model.User -> {
                        val kakaoUser = User(
                            id = userInfo.kakaoAccount?.email ?: "unknown",
                            userName = userInfo.kakaoAccount?.profile?.nickname ?: "unknown",
                            userProfileUri = userInfo.kakaoAccount?.profile?.thumbnailImageUrl
                        )
                        loginUseCase(Constants.SOCIAL_TYPE.KAKAO, kakaoUser)
                    }

                    else -> {
                        val guestUser = User(
                            id = "anonymous",
                            userName = "guest",
                            userProfileUri = null
                        )
                        loginUseCase(Constants.SOCIAL_TYPE.GUEST, guestUser)
                    }
                }
            }
        }
    }
}