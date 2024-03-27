package com.example.twist.model.data.database

import androidx.room.Database
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.twist.model.data.entity.RemoteEntity
import com.example.twist.model.data.entity.VideoEntity

@Database(
    entities = [VideoEntity::class, RemoteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class VideoDatabase: RoomDatabase() {
    abstract fun videoDao(): Dao
    abstract fun remoteDao(): RemoteDao

    companion object {
        @Volatile
        private var INSTANCE: VideoDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): VideoDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    VideoDatabase::class.java, "video_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}