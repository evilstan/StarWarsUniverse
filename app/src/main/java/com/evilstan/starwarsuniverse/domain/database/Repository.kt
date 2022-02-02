package com.evilstan.starwarsuniverse.domain.database

import com.evilstan.starwarsuniverse.domain.cache.PersonCache

class Repository(private val personDao: PersonDao) {

    suspend fun contains(name:String) = personDao.contains(name)

    fun getAllByLiveData() = personDao.getPersons()

    suspend fun getAll() = personDao.getAll()

    suspend fun insert(person: PersonCache) {
        personDao.insert(person)
    }

    suspend fun delete(person: PersonCache) {
        personDao.delete(person)
    }
}