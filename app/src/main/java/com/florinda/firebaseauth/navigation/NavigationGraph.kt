package com.florinda.firebaseauth.navigation

import LoginScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.florinda.firebaseauth.ui.screens.sing_in.LoginViewModel
import org.koin.androidx.compose.getKoin
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screens.SingInScreen.route
    ) {
        composable(route = Screens.SingInScreen.route) {
           // val viewModel  = getKoin().get<LoginViewModel>()
            val viewModel  = koinViewModel<LoginViewModel>()
               // route<LoginViewModel>()
            LoginScreen(
                state = viewModel.singInState
            )
        }
        composable(route = Screens.SingUpScreen.route) {

        }
    }

}