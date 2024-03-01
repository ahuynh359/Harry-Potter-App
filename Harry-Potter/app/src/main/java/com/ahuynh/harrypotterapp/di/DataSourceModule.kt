package com.ahuynh.harrypotterapp.di

import com.ahuynh.harrypotterapp.data.remote.RemoteDataSource
import com.ahuynh.harrypotterapp.data.remote.RemoteDataSourceImpl
import com.ahuynh.harrypotterapp.data.service.HarryPotterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object DataSourceModule {

    @Provides
    @ActivityRetainedScoped
    fun provideRemoteDataSource(harryPotterService: HarryPotterService) : RemoteDataSource{
        return RemoteDataSourceImpl(harryPotterService)
    }
}