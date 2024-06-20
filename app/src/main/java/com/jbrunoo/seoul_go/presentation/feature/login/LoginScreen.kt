package com.jbrunoo.seoul_go.presentation.feature.login


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jbrunoo.seoul_go.R
import com.jbrunoo.seoul_go.common.Constants

@Composable
fun LoginScreen(
    navigateToMain: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val auth = viewModel.auth.collectAsStateWithLifecycle()
    val isLoading = viewModel.isLoading.collectAsStateWithLifecycle()

    LaunchedEffect(auth.value) {
        if(auth.value != Constants.SOCIAL_TYPE.UNAUTHENTICATED.type) navigateToMain()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = auth.value)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.7f)
                .background(Color.Green),
            contentAlignment = Alignment.Center
        ) {
            Text("로그인 화면 이미지 추가 예정")
        }
        Column(
            modifier = Modifier.weight(0.3f),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            LoginButton(Constants.SOCIAL_TYPE.GOOGLE) {
                viewModel.googleLogin(context)
            }
            LoginButton(Constants.SOCIAL_TYPE.KAKAO) {
                viewModel.kakaoLogin(context)
            }
            LoginButton(Constants.SOCIAL_TYPE.GUEST) {
                viewModel.guestLogin()
            }
        }
    }

    if(isLoading.value) Box(modifier = Modifier.background(Color.Transparent)) {Text(text = "로딩 화면 추가 예정") }
}

@Composable
fun LoginButton(socialType: Constants.SOCIAL_TYPE, onClick: () -> Unit) {
    val color = when (socialType) {
        Constants.SOCIAL_TYPE.GOOGLE -> Color(0xFF131314)
        Constants.SOCIAL_TYPE.KAKAO -> Color(0xFFFEE500)
        else -> MaterialTheme.colorScheme.primary
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color, RoundedCornerShape(12.dp))
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        when (socialType) {
            Constants.SOCIAL_TYPE.GOOGLE -> {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .border(1.dp, Color(0xff8E918F), RoundedCornerShape(12.dp)),
                    imageVector = ImageVector.vectorResource(R.drawable.login_btn_google),
                    contentDescription = "google login button"
                )
            }
            Constants.SOCIAL_TYPE.KAKAO -> {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = ImageVector.vectorResource(R.drawable.login_btn_kakao),
                    contentDescription = "kakao login button"
                )
            }
            else -> {
                Text(
                    text = "게스트 로그인",
                    color = Color.White,
                    fontSize = 18.sp,
                )
            }
        }
    }
}