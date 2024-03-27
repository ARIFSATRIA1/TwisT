package com.example.twist.model.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteEntity(
    @PrimaryKey
    val id: String,

    val page: Int,

    val totalPage: Int,

    val total: Int,

    val prev: Int,

    val next: Int

)
