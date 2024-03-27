package com.example.twist.model.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "video")
data class VideoEntity(
    @PrimaryKey
    val id: String,

    val caption: String,

    val urlVideo: String,

    val likes: String,

    val dislikes: String,

    val messages: String
): Parcelable
