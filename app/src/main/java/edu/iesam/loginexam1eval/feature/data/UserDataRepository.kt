package edu.iesam.loginexam1eval.feature.data

import edu.iesam.loginexam1eval.feature.domain.User
import edu.iesam.loginexam1eval.feature.domain.UserRepository
import org.koin.core.annotation.Single

@Single
class UserDataRepository(
    private val local : LoginXmlLocalDataSource
): UserRepository {
     override fun findById(username:String) : User?{
        return local.findById(username)
    }
    override fun save(user:User) {
        return local.save(user)
    }
}