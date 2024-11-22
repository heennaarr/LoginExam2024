package edu.iesam.loginexam1eval.feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.loginexam1eval.feature.domain.GetUserByUserName
import edu.iesam.loginexam1eval.feature.domain.User
import edu.iesam.loginexam1eval.feature.domain.GetUsersUseCase
import edu.iesam.loginexam1eval.feature.domain.SaveUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel



@KoinViewModel
class UserViewModel(
    private val getUsersUseCase: GetUsersUseCase,
    private val saveUserUseCase: SaveUserUseCase,
    private val getUserByUserName: GetUserByUserName
) : ViewModel() {
    private var _uiState = MutableLiveData<UiState>()
    val uiState : LiveData<UiState> = _uiState


   fun loadUsers(){
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.postValue(UiState(users = getUsersUseCase.invoke()))
        }
    }
    /*   fun getUser(name: String){
          _uiState.value = UiState(isLoading = true)
          viewModelScope.launch(Dispatchers.IO) {
              _uiState.postValue(UiState(users = getUserByUserName.invoke(name)))
          }
      }*/

 /* fun saveUser(user : User){
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.postValue(UiState(users = user))
        }
    }*/




    data class UiState(
        val isLoading : Boolean = false,
        val errorApp: Throwable? = null,
        val users: List<User>? = null
    )
}