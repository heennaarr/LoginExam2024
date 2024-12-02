package edu.iesam.loginexam1eval.feature.domain

import org.koin.core.annotation.Single

@Single
class LoginUseCase(private val userRepository: UserRepository) {


    operator fun invoke(username: String, password: String): Boolean {
        val user: User? = userRepository.findById(username)

        return if (user != null && user.password == password) {
            true
        } else {
            false
        }
    }
}
