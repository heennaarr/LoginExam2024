package edu.iesam.loginexam1eval.feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.loginexam1eval.feature.domain.GetUserReminderUseCase
import edu.iesam.loginexam1eval.feature.domain.LoginUseCase
import edu.iesam.loginexam1eval.feature.domain.SetUserReminderUseCase
import edu.iesam.loginexam1eval.feature.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class LoginViewModel (
        private val loginUseCase: LoginUseCase,
        private val getReminderUseCase: GetUserReminderUseCase,
        private val setUserReminderUseCase: SetUserReminderUseCase
    ) : ViewModel() {
        private var _uiState = MutableLiveData<UiState>()
        val uiState : LiveData<UiState> = _uiState


        fun loadUsers(user:String , password:String){
            _uiState.value = UiState(isLoading = true)
            viewModelScope.launch(Dispatchers.IO) {
                _uiState.postValue(UiState(users = loginUseCase.invoke(user , password )))
            }
        }
    fun getLogin(){
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            val load = getReminderUseCase.invoke()
            _uiState.postValue(UiState(userReminder = load))
        }
    }

    fun setLogin(user: User?){
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            setUserReminderUseCase.invoke(user)
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val error: Throwable? = null,
        val users: Boolean = false,
        val userReminder: User? = null
    )
    }
