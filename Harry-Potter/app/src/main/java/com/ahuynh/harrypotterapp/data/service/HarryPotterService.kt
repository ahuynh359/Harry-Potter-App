package com.ahuynh.harrypotterapp.data.service

import com.ahuynh.harrypotterapp.data.model.Character
import retrofit2.http.GET
import retrofit2.http.Path

interface HarryPotterService {

    @GET("api/characters/house/{house}")
    suspend fun getCharacters(@Path("house") type: String): Character

}
