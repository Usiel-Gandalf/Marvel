package com.go.marvel.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.go.marvel.data.local.model.ComicEntity
import kotlinx.coroutines.flow.Flow

/**
[ComicDAO] with this interface we have contracts of queries what we can to do into database
@author Usiel Garcia Jimenez
 **/
@Dao
interface ComicDAO{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(Games: ComicEntity)

    @Query("SELECT * FROM ComicEntity")
    fun getComics(): Flow<MutableList<ComicEntity>>

    @Query("SELECT * FROM ComicEntity WHERE id=:id")
    fun getComicById(id: Int): List<ComicEntity>

    @Query("DELETE FROM ComicEntity WHERE id=:comicID")
    suspend fun delete(comicID: Int)
}