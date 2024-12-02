package edu.iesam.loginexam1eval.feature.domain

import org.koin.core.annotation.Single

@Single
class GetUsersUseCase(private val userRepository: UserRepository) {
    operator fun invoke(username:String ,  password:String): Boolean{
        val user: User? = userRepository.findById(username)
        if(user == null && password != null){
            userRepository.save(User(username, username, password))
            return true
        }else{
            return false
        }

    }
}