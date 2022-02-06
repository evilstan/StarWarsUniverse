package com.evilstan.starwarsuniverse.ui.core

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.evilstan.starwarsuniverse.domain.cache.PersonCache
import com.evilstan.starwarsuniverse.domain.database.AppDatabase
import com.evilstan.starwarsuniverse.domain.database.Repository
import kotlinx.coroutines.launch

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    private val personDao = AppDatabase.getInstance(application.applicationContext).personDao()
    private val repository = Repository.Base(personDao)
    val cachedPersons: LiveData<List<PersonCache>> = repository.persons()

    private var contains = false

    suspend fun dbContains(name: String): Boolean {
        viewModelScope.launch {
            contains = repository.contains(name)
        }.join()
        return contains
    }

    fun makeFavorite(personCache: PersonCache, favorite: Boolean) {
        personCache.favorite = favorite
        viewModelScope.launch {
            if (favorite) {
                repository.insert(personCache)
            } else {
                repository.delete(personCache)
            }
        }
    }
}