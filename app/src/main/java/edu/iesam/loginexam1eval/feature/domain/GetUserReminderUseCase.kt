package edu.iesam.loginexam1eval.feature.domain

import org.koin.core.annotation.Single
@Single
class GetUserReminderUseCase(private val userRepository: UserRepository) {
    operator fun invoke(): User? {
        return userRepository.findReminder()
    }
}