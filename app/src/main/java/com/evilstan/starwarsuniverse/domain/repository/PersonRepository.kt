package com.evilstan.starwarsuniverse.domain.repository

import androidx.lifecycle.LiveData
import com.evilstan.starwarsuniverse.domain.models.CharacterCache
import com.evilstan.starwarsuniverse.domain.models.CharacterUi

interface PersonRepository {

    suspend fun contains(name: String):Boolean

    fun persons(): LiveData<List<CharacterUi>>

    suspend fun insert(character: CharacterUi)


    suspend fun delete(character: CharacterUi)

}