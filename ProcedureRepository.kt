package com.example.myapplication

import io.ktor.client.request.get
import io.ktor.client.call.body
import kotlinx.coroutines.flow.MutableStateFlow

class ProcedureRepository {
    suspend fun getProcedureYumYum(idItem: String):Procedure{
        val response=KtorClient.httpClient
            .get("https://crudcrud.com/api/9d51aac4464f421a83ad0df9ab17dbb0/recipes_recipes/$idItem")
            .body<Procedure>()
        return response
    }
}
