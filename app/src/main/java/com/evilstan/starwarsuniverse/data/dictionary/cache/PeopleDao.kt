package com.evilstan.starwarsuniverse.data.dictionary.cache

import androidx.room.*
import com.evilstan.starwarsuniverse.data.dictionary.cache.PeopleCache

@Dao
interface PeopleDao {
    @Query("SELECT *FROM people")
    fun getPeople()

    @Query("SELECT*FROM people WHERE name =:name")
    fun getByName(name:String)

    @Insert
    fun insert(peopleCache: PeopleCache)

    @Update
    fun update(peopleCache: PeopleCache)

    @Delete
    fun delete(peopleCache: PeopleCache)
}