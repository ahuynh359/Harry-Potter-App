package com.ahuynh.harrypotterapp.data.remote

import com.ahuynh.harrypotterapp.data.model.Character
import com.ahuynh.harrypotterapp.data.service.HarryPotterService
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val service: HarryPotterService
) : RemoteDataSource {

    override suspend fun getCharacters(type: String): Character {
        return service.getCharacters(type)
    }
}