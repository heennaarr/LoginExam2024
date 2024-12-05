package edu.iesam.loginexam1eval.feature.domain

interface UserRepository {
    fun findById(username : String) : User?
    fun save( user : User)
    fun findReminder() : User?
    fun saveReminder(user: User?)

}