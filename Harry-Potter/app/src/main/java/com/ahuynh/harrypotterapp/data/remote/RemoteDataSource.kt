package com.ahuynh.harrypotterapp.data.remote

import com.ahuynh.harrypotterapp.data.model.Character

interface RemoteDataSource {
    suspend fun getCharacters(type : String) : Character
}
