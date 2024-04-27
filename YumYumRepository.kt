package com.example.myapplication

import io.ktor.client.call.body
import io.ktor.client.request.get

class YumYumRepository {
    suspend fun getYumYum(): List<Recipe>{
        val response = KtorClient.httpClient
            .get("https://crudcrud.com/api/9d51aac4464f421a83ad0df9ab17dbb0/recipes_recipes")
            .body<List<Recipe>>()
        return response
    }
}
