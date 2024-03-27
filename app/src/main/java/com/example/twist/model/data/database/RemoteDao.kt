package com.example.twist.model.data.database

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.twist.model.data.entity.RemoteEntity

interface RemoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun uploadVideo(remoteKey: RemoteEntity)

    @Query("SELECT * FROM remote_keys WHERE ID = :id")
    suspend fun getRemoteKeys(id: String): RemoteEntity?

    @Query("DELETE FROM remote_keys")
    suspend fun deleteRemoteKeys()
}