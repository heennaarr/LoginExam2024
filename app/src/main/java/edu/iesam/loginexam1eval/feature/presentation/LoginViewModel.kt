package edu.iesam.loginexam1eval.feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.loginexam1eval.feature.domain.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class LoginViewModel (
        private val loginUseCase: LoginUseCase,
    ) : ViewModel() {
        private var _uiState = MutableLiveData<UiState>()
        val uiState : LiveData<UiState> = _uiState


        fun loadUsers(user:String , password:String){
            _uiState.value = UiState(isLoading = true)
            viewModelScope.launch(Dispatchers.IO) {
                _uiState.postValue(UiState(users = loginUseCase.invoke(user , password )))
            }
        }

        data class UiState(
            val isLoading : Boolean = false,
            val errorApp: Throwable? = null,
            val users: Boolean? = null
        )
    }
