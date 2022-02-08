package com.evilstan.starwarsuniverse.data.repository

import androidx.lifecycle.LiveData
import com.evilstan.starwarsuniverse.data.database.PersonDao
import com.evilstan.starwarsuniverse.domain.models.CharacterCache
import com.evilstan.starwarsuniverse.domain.models.CharacterUi

interface PersonRepository {

    suspend fun contains(name: String): Boolean

    fun persons(): LiveData<List<CharacterUi>>

    suspend fun insert(character: CharacterCache)

    suspend fun delete(character: CharacterCache)


    class Base(private val personDao: PersonDao) : PersonRepository {

        override suspend fun contains(name: String) = personDao.contains(name)

        override fun persons() = personDao.persons()

        override suspend fun insert(character: CharacterCache) = personDao.insert(character)


        override suspend fun delete(character: CharacterCache) = personDao.delete(character)

    }
}