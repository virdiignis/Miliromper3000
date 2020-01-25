package com.example.alkoapp.data.models

data class User(
    val date_joined: String,
    val email: String,
    val first_name: String,
    val groups: List<Any>,
    val id: Int,
    val is_active: Boolean,
    val is_staff: Boolean,
    val is_superuser: Boolean,
    val last_login: Any,
    val last_name: String,
    val password: String,
    val user_permissions: List<Any>,
    val username: String
)