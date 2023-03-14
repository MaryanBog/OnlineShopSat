package com.example.onlineshopsat.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Repository
import com.example.onlineshopsat.presentation.sign.PersonaState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    var onAccountExisted = false

    private var _statePersonal = MutableStateFlow<PersonaState>(PersonaState.Start)
    val statePersonal = _statePersonal.asStateFlow()

    private var _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    fun isExisted(firstName: String){
        val firstNameLowerCase = firstName.lowercase()
        viewModelScope.launch {
            if (firstName.isEmpty()) {
                _errorMessage.emit("First Name is empty")
                _statePersonal.value = PersonaState.Error
            } else {
                onAccountExisted = repository.onAccountExisted(firstNameLowerCase)
                _statePersonal.value = PersonaState.Enter
            }
        }
    }

    fun onEnterAccount(){
        if (onAccountExisted){
            _statePersonal.value = PersonaState.Success
        } else {
            viewModelScope.launch {
                _statePersonal.value = PersonaState.Error
                _errorMessage.emit("This account does not exists")
            }
        }
    }

    fun reternPersonStateStart() {
        _statePersonal.value = PersonaState.Start
    }
}