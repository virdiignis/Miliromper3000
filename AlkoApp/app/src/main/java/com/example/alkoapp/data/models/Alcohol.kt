package com.example.alkoapp.data.models

data class Alcohol(
    val alcohol_content: String,
    val description: Any,
    val id: Int,
    val name: String,
    val producer: String,
    val production_country: String,
    val type: Type,
    val average_rating: String
)