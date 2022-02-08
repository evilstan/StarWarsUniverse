package com.evilstan.starwarsuniverse.ui.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evilstan.starwarsuniverse.cloud.core.App
import com.evilstan.starwarsuniverse.data.repository.PersonRepository
import com.evilstan.starwarsuniverse.domain.models.Character
import com.evilstan.starwarsuniverse.domain.models.CharacterCache
import com.evilstan.starwarsuniverse.domain.models.CharacterUi
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    private val database = App.database
    private val repository = PersonRepository.Base(database.personDao())

    val cachedPersons: LiveData<List<CharacterUi>> = repository.persons()


    suspend fun dbContains(name: String): Boolean {
        var contains = false

        viewModelScope.launch {
            contains = repository.contains(name)
        }.join()
        return contains
    }

    fun makeFavorite(character: Character, favorite: Boolean) {
        character.favorite = favorite
        viewModelScope.launch {
            if (favorite) {
                repository.insert(character as CharacterCache)
            } else {
                repository.delete(character as CharacterCache)
            }
        }
    }
}