package com.evilstan.starwarsuniverse.domain.cache

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PersonDao {
    @Query("SELECT *FROM persons")
    fun getPersons(): LiveData<List<PersonCache>>

    @Query("SELECT*FROM persons WHERE name =:name")
    fun getByName(name: String): PersonCache

    @Query("SELECT COUNT(*) FROM persons")
    suspend fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(personCache: PersonCache)

    @Update
    fun update(personCache: PersonCache)

    @Delete
    suspend fun delete(personCache: PersonCache)
}