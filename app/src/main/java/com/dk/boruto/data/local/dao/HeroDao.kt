package com.dk.boruto.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.dk.boruto.domain.model.Hero
import com.dk.boruto.util.Constants.HERO_DATABASE_TABLE

@Dao
interface HeroDao {

    @Query("SELECT * FROM $HERO_DATABASE_TABLE ORDER BY id ASC")
    fun getAllHeroes(): PagingSource<Int, Hero>

    @Query("SELECT * FROM $HERO_DATABASE_TABLE WHERE id=:heroId")
    fun getSelectedHero(heroId: Int): Hero

    @Upsert
    suspend fun addHeroes(heroes: List<Hero>)

    @Query("DELETE FROM hero_table")
    suspend fun deleteAllHeroes()
}