package com.example.alkoapp.data.models

data class Type(
    val general_type: String,
    val id: Int,
    val specific_type: String
)
{
    override fun toString(): String {
        return "$general_type, $specific_type"
    }
}

