package com.dk.boruto.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dk.boruto.data.local.dao.HeroDao
import com.dk.boruto.domain.model.Hero

@Database(
    entities = [Hero::class],
    version = 1
)
abstract class BorutoDatabase: RoomDatabase() {

    abstract fun heroDao(): HeroDao
}