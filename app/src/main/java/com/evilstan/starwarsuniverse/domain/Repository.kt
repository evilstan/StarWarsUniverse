package com.evilstan.starwarsuniverse.domain

import androidx.lifecycle.LiveData
import com.evilstan.starwarsuniverse.domain.cache.PersonCache
import com.evilstan.starwarsuniverse.domain.cache.PersonDao

class Repository(private val personDao: PersonDao) {

    val allPersons: LiveData<List<PersonCache>> = personDao.getPersons()

    suspend fun insert(person: PersonCache) {
        personDao.insert(person)
    }

    suspend fun delete(person: PersonCache) {
        personDao.delete(person)
    }

    suspend fun getCount() = personDao.getCount()
}