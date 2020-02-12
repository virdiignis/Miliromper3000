package com.example.alkoapp.data.repository

import com.example.alkoapp.data.models.Credentials
import com.example.alkoapp.data.network.AuthApi
import com.example.alkoapp.data.network.SafeApiRequest

class AuthRepository(
    private val api: AuthApi
) : SafeApiRequest() {
    suspend fun login(credentials: Credentials) = apiRequest { api.login(credentials) }
    suspend fun me(authToken: String) = apiRequest { api.me(authToken) }
}