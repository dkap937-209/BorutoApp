package com.dk.boruto.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dk.boruto.util.Constants.HERO_REMOTE_KEYS_TABLE

@Entity(tableName = HERO_REMOTE_KEYS_TABLE)
data class HeroRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val previousPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?
)
