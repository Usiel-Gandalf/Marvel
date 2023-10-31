package com.go.marvel.core.di

import android.content.Context
import androidx.room.Room
import com.go.marvel.data.local.ComicDatabase
import com.go.marvel.data.local.dao.ComicDAO
import com.go.marvel.utils.BuildConfig.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ComicDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideUserDao(database: ComicDatabase): ComicDAO = database.comicDAO()
}