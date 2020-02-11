package com.example.alkoapp.data.models

data class Rate(
    val alcohol: Int,
    val comment: String?,
    val favourite: Boolean,
    val id: Int,
    val rating: String,
    val user: Int
)