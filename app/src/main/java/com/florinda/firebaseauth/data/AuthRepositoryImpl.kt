package com.florinda.firebaseauth.data

import android.util.Log
import com.florinda.firebaseauth.util.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl constructor(
    val firebaseAuth : FirebaseAuth
): AuthRepository {

    override fun loginUser(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result: AuthResult = firebaseAuth.signInWithEmailAndPassword(email,password).await()
            Log.i("token_result",result.user?.email.toString())
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }.flowOn(Dispatchers.IO)
    }

    override fun registerUser(email: String, password: String): Flow<Resource<AuthResult>> {
        Log.i("token_result", " call")

        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.createUserWithEmailAndPassword(email,password).await()
            Log.i("token_result",result.user?.email.toString())
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }.flowOn(Dispatchers.IO)
    }

}