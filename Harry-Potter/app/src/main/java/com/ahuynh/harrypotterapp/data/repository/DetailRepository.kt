package com.ahuynh.harrypotterapp.data.repository

import com.ahuynh.harrypotterapp.data.model.Character

interface DetailRepository {

    suspend fun getCharacters(type : String) : Character

}
