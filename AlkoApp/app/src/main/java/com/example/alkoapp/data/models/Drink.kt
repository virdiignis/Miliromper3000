package com.example.alkoapp.data.models

data class Drink(
    val id: Int,
    val description: String,
    val glass: Any,
    val how_to_serve: String,
    val instruction: String,
    val name: String,
    val average_rating: String,
    val stuff: List<Any>
)