package com.jbrunoo.seoul_go.domain.useCase.auth

import com.jbrunoo.seoul_go.common.Constants
import com.jbrunoo.seoul_go.domain.model.User
import com.jbrunoo.seoul_go.domain.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(userId: String) {
        authRepository.setAuth(Constants.SOCIAL_TYPE.UNAUTHENTICATED)
        authRepository.deleteUserInfo(userId)
    }
}
