package com.go.marvel.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.go.marvel.utils.BuildConfig.Companion.MAIN_TABLE_NAME

@Entity(tableName = MAIN_TABLE_NAME)
data class ComicEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
    @ColumnInfo(name = "description") val description: String,
)