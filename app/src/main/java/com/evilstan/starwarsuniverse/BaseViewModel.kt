package com.evilstan.starwarsuniverse

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evilstan.starwarsuniverse.domain.cache.PersonCache
import com.evilstan.starwarsuniverse.domain.database.AppDatabase
import com.evilstan.starwarsuniverse.domain.database.Repository
import kotlinx.coroutines.launch

abstract class BaseViewModel(context: Context) : ViewModel() {
    private val movieDao = AppDatabase.getInstance(context).personDao()
    private val repository = Repository(movieDao)
    val cachedPersons: LiveData<List<PersonCache>> = repository.getAllByLiveData()

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