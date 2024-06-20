package com.jbrunoo.seoul_go.domain.useCase.auth

import com.jbrunoo.seoul_go.common.Constants
import com.jbrunoo.seoul_go.domain.model.User
import com.jbrunoo.seoul_go.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(socialType: Constants.SOCIAL_TYPE, userInfo: User) {
        authRepository.setAuth(socialType)
        authRepository.setUserInfo(userInfo)
    }
}