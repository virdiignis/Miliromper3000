package com.example.alkoapp.data.models

data class AlcoholRating(
    val alcohol: Int,
    val rating: String?,
    val comment: String?,
    val favourite: Boolean,
    val user: Int,
    val id: Int?
)
