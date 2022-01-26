package com.evilstan.starwarsuniverse.data.dictionary.cache

import androidx.room.*
import com.evilstan.starwarsuniverse.data.dictionary.cache.PeopleCache

interface FilmsDao {
    @Query("SELECT *FROM films")
    fun getPeople()

    @Query("SELECT*FROM films WHERE title =:title")
    fun getByTitle(title: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(films:List<FilmCache>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(filmCache: FilmCache)

    @Update
    fun update(filmCache: FilmCache)

    @Delete
    fun delete(filmCache: FilmCache)
}