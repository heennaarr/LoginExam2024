package edu.iesam.loginexam1eval.feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.loginexam1eval.feature.domain.GetUsersUseCase
import edu.iesam.loginexam1eval.feature.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel



@KoinViewModel
class UserViewModel(
    private val getUsersUseCase: GetUsersUseCase,
) : ViewModel() {
    private var _uiState = MutableLiveData<UiState>()
    val uiState : LiveData<UiState> = _uiState


   fun loadUsers(user:String){
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.postValue(UiState(users = getUsersUseCase.invoke(user)))
        }
    }

    data class UiState(
        val isLoading : Boolean = false,
        val errorApp: Throwable? = null,
        val users: Boolean? = null
    )
}