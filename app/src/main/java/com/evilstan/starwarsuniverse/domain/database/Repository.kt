package com.evilstan.starwarsuniverse.domain.database

import androidx.lifecycle.LiveData
import com.evilstan.starwarsuniverse.domain.cache.PersonCache

interface Repository {

    suspend fun contains(name: String): Boolean

    fun getPersons(): LiveData<List<PersonCache>>

    suspend fun insert(person: PersonCache)

    suspend fun delete(person: PersonCache)


    class Base(private val personDao: PersonDao) : Repository {

        override suspend fun contains(name: String) = personDao.contains(name)

        override fun getPersons() = personDao.getPersons()

        override suspend fun insert(person: PersonCache) {
            personDao.insert(person)
        }

        override suspend fun delete(person: PersonCache) {
            personDao.delete(person)
        }
    }
}