package edu.iesam.loginexam1eval.feature.domain

import org.koin.core.annotation.Single

@Single
class SaveUserUseCase(private val userRepository: UserRepository){
    operator fun invoke(user:User) {
        return userRepository.save(user)
    }
}