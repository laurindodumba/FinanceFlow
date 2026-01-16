package com.example.financeflowv1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // State para email e senha
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _senha = MutableStateFlow("")
    val senha: StateFlow<String> = _senha

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _success = MutableStateFlow(false)
    val success: StateFlow<Boolean> = _success

    // Atualizar email
    fun setEmail(value: String) {
        _email.value = value
    }

    // Atualizar senha
    fun setSenha(value: String) {
        _senha.value = value
    }

    // Função de login
    fun login() {
        viewModelScope.launch {
            auth.signInWithEmailAndPassword(_email.value, _senha.value)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _success.value = true
                    } else {
                        _error.value = task.exception?.message
                    }
                }
        }
    }
}
