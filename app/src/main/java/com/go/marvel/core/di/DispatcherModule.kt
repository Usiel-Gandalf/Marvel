package com.go.marvel.core.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {


}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IODispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDisplatcher