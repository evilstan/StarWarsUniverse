package com.evilstan.starwarsuniverse.domain

import android.content.Context
import com.evilstan.starwarsuniverse.domain.cache.PersonCache
import com.evilstan.starwarsuniverse.domain.cache.PersonDao

class DatabaseManager(context: Context) {
    private val appDatabase = AppDatabase(context)

    var personDao: PersonDao = appDatabase.personDao()

    fun putPerson(person: PersonCache) {
        personDao.insert(person)
    }

    fun deletePerson(person: PersonCache) {
        personDao.delete(person)
    }

    fun getByName(name: String) = personDao.getByName(name)

    fun getAll() = personDao.getPeople()

    fun getFavorites() = personDao.getFavorites()


}