package com.dk.boruto.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.dk.boruto.domain.model.HeroRemoteKeys
import com.dk.boruto.util.Constants.HERO_REMOTE_KEYS_TABLE

@Dao
interface HeroRemoteKeysDao {

    @Query("SELECT * FROM $HERO_REMOTE_KEYS_TABLE WHERE id = :heroId")
    suspend fun getRemoteKeys(heroId: Int): HeroRemoteKeys?

    @Upsert
    suspend fun addAllRemoteKeys(heroRemoteKeys: List<HeroRemoteKeys>)

    @Query("DELETE FROM $HERO_REMOTE_KEYS_TABLE")
    suspend fun deleteAllRemoteKeys()
}