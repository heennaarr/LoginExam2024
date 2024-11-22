package edu.iesam.loginexam1eval.feature.domain

import org.koin.core.annotation.Single

@Single
class GetUserByUserName(private val userRepository: UserRepository) {
    operator fun invoke(name:String): User?{
        return userRepository.findByUserName(name)
    }
}