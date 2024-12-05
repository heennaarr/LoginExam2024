package edu.iesam.loginexam1eval.feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.loginexam1eval.feature.domain.DeleteUserUseCase
import edu.iesam.loginexam1eval.feature.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class BajasViewModel (
    private val deleteUserUseCase: DeleteUserUseCase
): ViewModel() {

    private val _uiState= MutableLiveData<UiState>()

    val uiState : LiveData<UiState> = _uiState




    fun delete(user: User) {

        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch (Dispatchers.IO) {

            val delete = deleteUserUseCase.invoke(user)

            _uiState.postValue(UiState(delete = delete))

        }

    }

    data class UiState (
        val isLoading: Boolean = false,
        val error: Throwable? = null,
        val delete: Boolean ?= false

    )

}