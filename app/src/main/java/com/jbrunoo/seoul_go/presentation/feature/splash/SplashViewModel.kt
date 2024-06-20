package com.jbrunoo.seoul_go.presentation.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jbrunoo.seoul_go.common.Constants
import com.jbrunoo.seoul_go.domain.useCase.auth.GetAuthUseCase
import com.jbrunoo.seoul_go.presentation.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getAuthUseCase: GetAuthUseCase
) : ViewModel() {
    fun checkLogin(onResult: (String) -> Unit) {
        viewModelScope.launch {
            val socialType = withContext(Dispatchers.IO) {
                getAuthUseCase().first()
            }
            val navDestination = when (socialType) {
                Constants.SOCIAL_TYPE.UNAUTHENTICATED.type -> Route.LOGIN
                else -> Route.MAIN
            }
            onResult(navDestination)
        }
    }
}