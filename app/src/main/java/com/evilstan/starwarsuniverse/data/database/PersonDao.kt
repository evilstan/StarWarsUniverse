package com.evilstan.starwarsuniverse.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.evilstan.starwarsuniverse.domain.models.CharacterCache
import com.evilstan.starwarsuniverse.domain.models.CharacterUi

@Dao
interface PersonDao {
    @Query("SELECT EXISTS (SELECT 1 FROM persons WHERE name = :name)")
    suspend fun contains(name: String): Boolean

    @Query("SELECT * FROM persons")
    fun persons(): LiveData<List<CharacterUi>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characterCache: CharacterUi)

    @Update
    fun update(characterCache: CharacterUi)

    @Delete
    suspend fun delete(characterCache: CharacterUi)
}