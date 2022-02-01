package com.evilstan.starwarsuniverse.ui.search

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.evilstan.starwarsuniverse.BaseViewModel
import com.evilstan.starwarsuniverse.Event
import com.evilstan.starwarsuniverse.data.dictionary.PersonCloud
import com.evilstan.starwarsuniverse.domain.AppDatabase
import com.evilstan.starwarsuniverse.domain.Repository
import com.evilstan.starwarsuniverse.domain.cache.PersonCache

class SearchViewModel(context: Context) : BaseViewModel() {

    val personsFromCloud = MutableLiveData<Event<ArrayList<PersonCloud>>>()
    private val personsFromDb = MutableLiveData<List<PersonCache>>()

    val allPersons = MutableLiveData<Event<ArrayList<PersonCloud>>>()
    private val movieDao = AppDatabase.getInstance(context).personDao()
    private val repository = Repository(movieDao)


    fun getUsers(name: String) {
        requestWithLiveData(personsFromCloud) {
            api.search(name)
        }
    }

    fun getPersonsFromDb() {
        getPersons(repository, personsFromDb)
    }

    fun insertToDb(personCache: PersonCache) {
        insert(personCache, repository)
    }

    fun deleteFromDb(personCache: PersonCache) {
        delete(personCache, repository)
    }
}