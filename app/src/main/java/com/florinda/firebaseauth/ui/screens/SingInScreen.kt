package com.florinda.firebaseauth.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun SingInScreen(

) {

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(

        ) {

        }
    }

}


@Composable
fun Password(
    password: (String) -> Unit
) {
    val passwordValue = rememberSaveable { mutableStateOf("") }

    LaunchedEffect(key1 = passwordValue){
        password(passwordValue.value)
    }



}


@Composable
fun UserName() {

}