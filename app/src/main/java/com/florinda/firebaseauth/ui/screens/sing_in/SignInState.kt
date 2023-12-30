package com.florinda.firebaseauth.ui.screens.sing_in

data class SignInState(
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = ""
)