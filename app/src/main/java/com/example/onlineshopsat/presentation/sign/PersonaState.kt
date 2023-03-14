package com.example.onlineshopsat.presentation.sign

sealed class PersonaState {
    object Start : PersonaState()
    object Success : PersonaState()
    object Error : PersonaState()
    object Enter : PersonaState()
    class Exists(
        val lastName: String,
        val email: String,
        val firstName: String
    ) : PersonaState()
}