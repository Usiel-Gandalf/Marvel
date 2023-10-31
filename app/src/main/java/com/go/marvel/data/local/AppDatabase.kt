package com.go.marvel.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.go.marvel.data.local.dao.ComicDAO
import com.go.marvel.data.local.model.ComicEntity

/**
 * Database Logic
@author Usiel Garcia Jimenez
 **/
@Database(
    entities = [
        ComicEntity::class,
    ], version = 1
)

abstract class ComicDatabase : RoomDatabase() {
    abstract fun comicDAO(): ComicDAO
}