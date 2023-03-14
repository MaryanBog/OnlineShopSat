package com.example.onlineshopsat.presentation.main

import androidx.lifecycle.ViewModel
import com.example.onlineshopsat.presentation.sign.PersonaState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private var _statePersonal = MutableStateFlow<PersonaState>(PersonaState.Start)
    val statePersonal = _statePersonal.asStateFlow()

    fun reternPersonStateStart() {
        _statePersonal.value = PersonaState.Start
    }
}