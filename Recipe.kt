package com.example.myapplication
import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    val id: String = "",
    val item:String = "",
    val imageUrl:String = "",
    val recipe : String=""
)
