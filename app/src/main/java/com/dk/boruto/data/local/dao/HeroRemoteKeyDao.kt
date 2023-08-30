package com.dk.boruto.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dk.boruto.domain.model.HeroRemoteKey
import com.dk.boruto.util.Constants.HERO_REMOTE_KEY_TABLE

@Dao
interface HeroRemoteKeyDao {

    @Query("SELECT * FROM $HERO_REMOTE_KEY_TABLE WHERE id = :id")
    suspend fun getRemoteKey(id: Int): HeroRemoteKey?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(heroRemoteKeys: List<HeroRemoteKey>)

    @Query("DELETE FROM $HERO_REMOTE_KEY_TABLE")
    suspend fun deleteAllRemoteKeys()
}