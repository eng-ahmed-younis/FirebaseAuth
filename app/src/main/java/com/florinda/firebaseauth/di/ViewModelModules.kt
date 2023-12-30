package com.florinda.firebaseauth.di

import com.florinda.firebaseauth.ui.screens.sing_in.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.lazyModule


val viewModelModules = lazyModule {
    viewModel {
        LoginViewModel(get())
    }
}