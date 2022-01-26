package com.evilstan.starwarsuniverse.data.dictionary.cache

import androidx.room.*


@Dao
interface HomeWorldDao {
    @Query("SELECT *FROM homeworlds")
    fun getPeople()

    @Query("SELECT*FROM homeworlds WHERE name =:name")
    fun getByTitle(name: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(homeWorldCache: HomeWorldCache)

    @Update
    fun update(homeWorldCache: HomeWorldCache)

    @Delete
    fun delete(homeWorldCache: HomeWorldCache)
}