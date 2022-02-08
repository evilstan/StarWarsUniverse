package com.evilstan.starwarsuniverse.data.repository

import com.evilstan.starwarsuniverse.data.database.PersonDao
import com.evilstan.starwarsuniverse.domain.models.CharacterCache
import com.evilstan.starwarsuniverse.domain.repository.PersonRepository

class PersonRepositoryImpl(private val personDao: PersonDao): PersonRepository {

         override suspend fun contains(name: String) = personDao.contains(name)

         override fun persons() = personDao.persons()

         override suspend fun insert(character: CharacterCache) = personDao.insert(character)


         override suspend fun delete(character: CharacterCache) = personDao.delete(character)

    }
