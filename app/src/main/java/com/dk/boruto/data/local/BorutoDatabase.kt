package com.dk.boruto.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dk.boruto.data.local.dao.HeroDao
import com.dk.boruto.data.local.dao.HeroRemoteKeysDao
import com.dk.boruto.domain.model.Hero
import com.dk.boruto.domain.model.HeroRemoteKeys

@Database(
    entities = [Hero::class, HeroRemoteKeys::class],
    version = 1
)
@TypeConverters(DatabaseConverter::class)
abstract class BorutoDatabase: RoomDatabase() {

    companion object {

        fun create(context: Context, useInMemory: Boolean): BorutoDatabase {
//            val databaseBuilder = if(useInMemory){
//                Room.inMemoryDatabaseBuilder(context, BorutoDatabase::class.java)
//            }
//            else{
//                Room.databaseBuilder(context, BorutoDatabase::class.java, "test_database.db")
//            }
//
//            return databaseBuilder
//                .fallbackToDestructiveMigration()
//                .build()

            return (if(useInMemory){
                Room.inMemoryDatabaseBuilder(context, BorutoDatabase::class.java)
            }
            else{
                Room.databaseBuilder(context, BorutoDatabase::class.java, "test_database.db")
            })
            .fallbackToDestructiveMigration()
            .build()
        }

    }

    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeysDao(): HeroRemoteKeysDao
}