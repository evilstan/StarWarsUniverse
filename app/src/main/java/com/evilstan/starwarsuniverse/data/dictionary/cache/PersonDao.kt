package com.evilstan.starwarsuniverse.data.dictionary.cache

import androidx.room.*

@Dao
interface PersonDao {
    @Query("SELECT *FROM people")
    fun getPeople()

    @Query("SELECT*FROM people WHERE name =:name")
    fun getByName(name:String)

    @Insert
    fun insert(personCache: PersonCache)

    @Update
    fun update(personCache: PersonCache)

    @Delete
    fun delete(personCache: PersonCache)
}