package com.florinda.firebaseauth.ui.screens.sing_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.florinda.firebaseauth.data.AuthRepository
import com.florinda.firebaseauth.util.Resource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel constructor(
    private val repository: AuthRepository
): ViewModel() {

    val _singInState = Channel<SignInState> ()
    val singInState = _singInState.receiveAsFlow()

    init {
        login("ahmedali4949@gmail.com","080099878")
    }

    fun login(email: String , password : String) = viewModelScope.launch {
        repository.registerUser(email,password).collect{result ->
            when(result){
                is Resource.Loading ->{
                    _singInState.send(SignInState(isLoading = true))
                }
                is Resource.Success ->{
                    _singInState.send(SignInState(isSuccess = "sing in success"))
                }

                is Resource.Error ->{
                    _singInState.send(SignInState(isError = result.message))
                }
            }
        }
    }

}