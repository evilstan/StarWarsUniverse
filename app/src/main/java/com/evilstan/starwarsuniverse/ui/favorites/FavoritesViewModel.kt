package com.evilstan.starwarsuniverse.ui.favorites

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.evilstan.starwarsuniverse.BaseViewModel
import com.evilstan.starwarsuniverse.domain.AppDatabase
import com.evilstan.starwarsuniverse.domain.Repository
import com.evilstan.starwarsuniverse.domain.cache.PersonCache
import kotlinx.coroutines.launch

class FavoritesViewModel(context: Context): BaseViewModel() {
    private val movieDao = AppDatabase.getInstance(context).personDao()
    private val repository= Repository(movieDao)
    val allPersons: LiveData<List<PersonCache>> = repository.allPersons


    fun insert(personCache: PersonCache) {
        insert(personCache, repository)
    }

    fun delete(personCache: PersonCache) {
        delete(personCache, repository)
    }
}