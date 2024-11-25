package edu.iesam.loginexam1eval.feature.data

import android.content.Context
import com.google.gson.Gson
import edu.iesam.loginexam1eval.feature.domain.User
import edu.iesam.loginexam1eval.feature.domain.UserRepository
import org.koin.core.annotation.Single

@Single
class LoginXmlLocalDataSource (private val context: Context) : UserRepository {

    private val sharedPref = context.getSharedPreferences(
        "user-storage", Context.MODE_PRIVATE
    )

    private val gson = Gson()

    override fun save(user: User) {
        val editor = sharedPref.edit()
        editor.putString(user.id, gson.toJson(user))
        editor.putString(user.name, gson.toJson(user))
        editor.putString(user.password, gson.toJson(user))
        editor.putString(user.reminderUser, gson.toJson(user))
        editor.apply()
    }

    fun saveAll(users: List<User>) {
        val editor = sharedPref.edit()
        users.forEach { user ->
            editor.putString(user.id, gson.toJson(user))
            editor.putString(user.name, gson.toJson(user))
            editor.putString(user.password, gson.toJson(user))
            editor.putString(user.reminderUser, gson.toJson(user))
        }
        editor.apply()
    }

    override fun findAll(): List<User>{
        val users = ArrayList<User>()
        val mapUsers = sharedPref.all //as Map<String, String>
        mapUsers.values.forEach { jsonUser ->
            val user = gson.fromJson(jsonUser as String, User::class.java)
            users.add(user)
        }
        return users
    }

    fun findById(userId: String): User?{
        return sharedPref.getString(userId, null)?.let { user ->
            gson.fromJson(user, User::class.java)
        }
    }
    override fun findByUserName(userName: String): Boolean?{
        return sharedPref.getString(userName, null)?.let { user ->
            gson.fromJson(user, User::class.java) as Boolean?
        }
    }

    fun delete() {
        sharedPref.edit().clear().apply()
    }

    fun deleteById(movieId: String){
        sharedPref.edit().remove(movieId).commit()
    }
}