package com.florinda.firebaseauth.navigation

sealed class Screens (val route : String){
    object SingInScreen : Screens("sing_in")
    object SingUpScreen : Screens("sing_up")
}