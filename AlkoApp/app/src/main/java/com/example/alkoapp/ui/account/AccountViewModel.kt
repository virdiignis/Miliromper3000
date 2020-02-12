package com.example.alkoapp.ui.account

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.alkoapp.data.models.Credentials
import com.example.alkoapp.data.models.Token
import com.example.alkoapp.data.models.User
import com.example.alkoapp.data.network.AuthApi
import com.example.alkoapp.data.repository.AuthRepository
import com.example.alkoapp.util.Coroutines
import kotlinx.coroutines.Job

class AccountViewModel() : ViewModel() {
    private val api = AuthApi()
    private val repository = AuthRepository(api)

    suspend fun login(credentials: Credentials): Token {
        return repository.login(credentials)
    }

    suspend fun me(authToken: String): User {
        val authToken2 = "Token $authToken"
        return repository.me(authToken2)
    }
}