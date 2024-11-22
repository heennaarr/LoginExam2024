package edu.iesam.loginexam1eval.feature.domain

interface UserRepository {
    fun findAll(): List<User>
    fun save(user: User)
    fun findByUserName(name:String):User?
}