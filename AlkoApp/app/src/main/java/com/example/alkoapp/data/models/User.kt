package com.example.alkoapp.data.models

data class User(
    val email: String,
    val first_name: String,
    val id: Int,
    val last_name: String,
    val password: String?,
    val username: String
)