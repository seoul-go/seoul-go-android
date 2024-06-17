package com.jbrunoo.seoul_go.domain.useCase.auth

import com.jbrunoo.seoul_go.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAuthUseCase @Inject constructor(private val authRepository: AuthRepository) {
    operator fun invoke(): Flow<String> = authRepository.getAuth()
}