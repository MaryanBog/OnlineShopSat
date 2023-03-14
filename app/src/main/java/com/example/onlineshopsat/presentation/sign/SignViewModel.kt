package com.example.onlineshopsat.presentation.sign

import android.text.TextUtils
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Repository
import com.example.data.db.models.PersonEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    var onAccountExisted = false

    private var _statePersonal = MutableStateFlow<PersonaState>(PersonaState.Start)
    val statePersonal = _statePersonal.asStateFlow()

    private var _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    fun onEmailValid(email: String, lastName: String, firstName: String) {
        val isValidEmail =
            !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches()

        if (onAccountExisted) {
            viewModelScope.launch {
                _statePersonal.value = PersonaState.Error
                _errorMessage.emit("This account already exists")
            }
        } else {
            if (isValidEmail) {
                _statePersonal.value = PersonaState.Success
                viewModelScope.launch {
                    repository.savePersonData(
                        personData = PersonEntity(
                            id = 0L, firstName = firstName, lastName = lastName, email = email
                        )
                    )
                }
            } else {
                viewModelScope.launch {
                    _statePersonal.value = PersonaState.Error
                    _errorMessage.emit("Email not valid")
                }
            }
        }
    }

    fun isExisted(firstName: String, lastName: String, email: String) {
        val firstNameLowerCase = firstName.lowercase()
        val lastNameLowerCase = lastName.lowercase()
        val emailLowerCase = email.lowercase()

        viewModelScope.launch {
            if (firstName.isEmpty()) {
                _errorMessage.emit("First Name is empty")
                _statePersonal.value = PersonaState.Error
            } else {
                onAccountExisted = repository.onAccountExisted(firstNameLowerCase)
                _statePersonal.value = PersonaState.Exists(
                    lastName = lastNameLowerCase,
                    email = emailLowerCase,
                    firstName = firstNameLowerCase
                )
            }
        }
    }

    fun reternPersonStateStart() {
        _statePersonal.value = PersonaState.Start
    }
}