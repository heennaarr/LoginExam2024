package edu.iesam.loginexam1eval.feature.domain

import org.koin.core.annotation.Single

@Single
class SetUserReminderUseCase (private val userRepository: UserRepository) {
        operator fun invoke(user :User?) {
            return userRepository.saveReminder(user)
        }
    }
