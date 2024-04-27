package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class YumYumViewModel: ViewModel() {
    private var _yummy = MutableStateFlow<List<Recipe>>(emptyList())
    val yummy:StateFlow<List<Recipe>> = _yummy

    init {
        //call the data
        fetchYumYum()
    }

    private fun fetchYumYum(){
        viewModelScope.launch {
            val repo = YumYumRepository()
            val response = repo.getYumYum()
            _yummy.value=response
        }
    }

}


