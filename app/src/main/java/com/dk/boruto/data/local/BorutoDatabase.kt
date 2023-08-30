package com.dk.boruto.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dk.boruto.data.local.dao.HeroDao
import com.dk.boruto.data.local.dao.HeroRemoteKeyDao
import com.dk.boruto.domain.model.Hero
import com.dk.boruto.domain.model.HeroRemoteKey

@Database(
    entities = [Hero::class, HeroRemoteKey::class],
    version = 1
)
abstract class BorutoDatabase: RoomDatabase() {

    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeyDao
}