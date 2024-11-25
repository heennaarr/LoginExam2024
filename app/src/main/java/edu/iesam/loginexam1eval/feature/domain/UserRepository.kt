package edu.iesam.loginexam1eval.feature.domain

interface UserRepository {
    fun findByUserName(name:String): Boolean?
}