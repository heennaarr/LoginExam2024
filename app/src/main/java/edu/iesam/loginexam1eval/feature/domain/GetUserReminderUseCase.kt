package edu.iesam.loginexam1eval.feature.domain

import org.koin.core.annotation.Single
@Single
class GetUserReminderUseCase(private val userRepository: UserRepository) {
    operator fun invoke(username: String, password: String): User? {
        val isReminder = userRepository.findReminder(username)
        if (isReminder == null ) {
            val user = User(username, username, password)
            userRepository.saveReminder(user)
            return user
        } else {
            return null
        }
    }
}