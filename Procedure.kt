package com.example.myapplication

import kotlinx.serialization.Serializable

@Serializable
data class Procedure(
    val id: String = "",
    val item:String = "",
    val imageUrl:String = "",
    val recipe : String=""
)
