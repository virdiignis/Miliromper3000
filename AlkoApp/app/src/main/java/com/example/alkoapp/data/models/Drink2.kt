package com.example.alkoapp.data.models

data class Drink2(
    val alcohols: List<Any>,
    val description: String,
    val glass: String,
    val how_to_serve: String,
    val ingredients: List<IngredientProportions>,
    val instruction: String,
    val name: String,
    val stuff: List<String>
)