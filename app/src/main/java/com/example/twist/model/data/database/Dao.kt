package com.example.twist.model.data.database

import androidx.paging.PagingSource
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.twist.model.data.entity.VideoEntity

interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun uploadVideo(video: VideoEntity)

    @Query("SELECT * FROM video")
    fun getAllVideo():PagingSource<Int, VideoEntity>

    @Query("DELETE FROM video")
    fun deleteAllVideo()
    
}