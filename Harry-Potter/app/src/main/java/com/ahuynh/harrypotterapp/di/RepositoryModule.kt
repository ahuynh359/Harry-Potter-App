package com.ahuynh.harrypotterapp.di

import com.ahuynh.harrypotterapp.data.remote.RemoteDataSource
import com.ahuynh.harrypotterapp.data.repository.DetailRepository
import com.ahuynh.harrypotterapp.data.repository.DetailRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {
    @Provides
    @ActivityRetainedScoped
    fun provideDetailRepository(remoteDataSource: RemoteDataSource): DetailRepository {
        return DetailRepositoryImpl(remoteDataSource)
    }
}