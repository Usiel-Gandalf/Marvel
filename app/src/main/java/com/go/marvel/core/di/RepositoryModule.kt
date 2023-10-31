package com.go.marvel.core.di

import com.go.marvel.data.remote.models.APIComicsRepository
import com.go.marvel.domain.repository.IComicsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideComicsRepository(implementation: APIComicsRepository): IComicsRepository
}