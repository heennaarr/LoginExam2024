package edu.iesam.loginexam1eval.feature.domain

import org.koin.core.annotation.Single

@Single
class GetUsersUseCase(private val userRepository: UserRepository) {
    operator fun invoke(username:String): Boolean? {
         return userRepository.findByUserName(username)
    }
}