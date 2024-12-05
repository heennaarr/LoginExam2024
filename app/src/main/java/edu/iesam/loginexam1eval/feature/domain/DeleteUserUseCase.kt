package edu.iesam.loginexam1eval.feature.domain

import org.koin.core.annotation.Single

@Single
class DeleteUserUseCase (private val userRepository: UserRepository) {
    operator fun invoke(user: User) : Boolean {
        // Buscar el usuario por su ID
        val userGet = userRepository.findById(user.id)
        val rememberUser = userRepository.findReminder()

        // Si encontramos el usuario
        if (userGet != null) {
            // Verificar si la contraseña coincide
            if (userGet.password == user.password) {
                // Eliminar el usuario (usamos el ID para eliminarlo)
                userRepository.deleteUser(userGet)
                return true
            }
        }

        // Si hay un usuario guardado como recordado
        if (rememberUser != null) {
            // Verificar si el nombre de usuario coincide
            if (rememberUser.name == user.name) {
                // Limpiar los datos del usuario recordado
                userRepository.saveReminder(User("", "", ""))
            }
        }

        return false
    }

}