package com.ahuynh.harrypotterapp.data.repository

import com.ahuynh.harrypotterapp.data.model.Character
import com.ahuynh.harrypotterapp.data.remote.RemoteDataSource
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val remoteDataSouce : RemoteDataSource
) : DetailRepository {

    override suspend fun getCharacters(type: String): Character {
        return remoteDataSouce.getCharacters(type)
    }
}