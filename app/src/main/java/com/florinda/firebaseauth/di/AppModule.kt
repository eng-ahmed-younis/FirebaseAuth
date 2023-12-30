package com.florinda.firebaseauth.di

import com.florinda.firebaseauth.data.AuthRepository
import com.florinda.firebaseauth.data.AuthRepositoryImpl
import com.florinda.firebaseauth.ui.screens.sing_in.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.lazyModule

val appModule = lazyModule {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<FirebaseAuth> {  Firebase.auth }
}



