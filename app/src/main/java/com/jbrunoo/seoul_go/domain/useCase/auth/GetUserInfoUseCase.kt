package com.jbrunoo.seoul_go.domain.useCase.auth

import com.jbrunoo.seoul_go.domain.model.User
import com.jbrunoo.seoul_go.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(private val authRepository: AuthRepository) {
    operator fun invoke(userId: String): Flow<User> = authRepository.getUserInfo(userId)
}