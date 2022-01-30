package com.evilstan.starwarsuniverse.domain.cache

import androidx.room.*

@Dao
interface PersonDao {
    @Query("SELECT *FROM people")
    fun getPeople(): List<PersonCache>

    @Query("SELECT*FROM people WHERE favorite = 1")
    fun getFavorites(): List<PersonCache>

    @Query("SELECT*FROM people WHERE name =:name")
    fun getByName(name: String): PersonCache

    @Insert
    fun insert(personCache: PersonCache)

    @Update
    fun update(personCache: PersonCache)

    @Delete
    fun delete(personCache: PersonCache)
}