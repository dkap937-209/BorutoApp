package com.dk.boruto.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dk.boruto.util.Constants.HERO_REMOTE_KEY_TABLE

@Entity(tableName = HERO_REMOTE_KEY_TABLE)
data class HeroRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val previousPage: Int?,
    val nextPage: Int?
)
