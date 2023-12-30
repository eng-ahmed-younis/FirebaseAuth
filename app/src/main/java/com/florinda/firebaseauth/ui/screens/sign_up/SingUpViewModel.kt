package com.florinda.firebaseauth.ui.screens.sign_up

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.florinda.firebaseauth.data.AuthRepository
import com.florinda.firebaseauth.util.Resource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SingUpViewModel constructor(
    private val repository: AuthRepository
): ViewModel() {

    val _singUpState = Channel<SignUpState> ()
    val singUpState = _singUpState.receiveAsFlow()



    fun login(email: String , password : String) = viewModelScope.launch {
        repository.loginUser(email,password).collect{result ->
            when(result){
                is Resource.Loading ->{
                    _singUpState.send(SignUpState(isLoading = true))
                }
                is Resource.Success ->{
                    _singUpState.send(SignUpState(isSuccess = "sing up success"))
                }

                is Resource.Error ->{
                    _singUpState.send(SignUpState(isError = result.message))
                }
            }
        }
    }

}