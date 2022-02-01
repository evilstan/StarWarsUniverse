package com.evilstan.starwarsuniverse

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evilstan.starwarsuniverse.data.core.NetModule
import com.evilstan.starwarsuniverse.data.core.ResponseWrapper
import com.evilstan.starwarsuniverse.data.core.StarWarsApi
import com.evilstan.starwarsuniverse.domain.Repository
import com.evilstan.starwarsuniverse.domain.cache.PersonCache
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    var api: StarWarsApi = NetModule().service(StarWarsApi::class.java)

    fun <T> requestWithLiveData(
        liveData: MutableLiveData<Event<T>>,
        request: suspend () -> ResponseWrapper<T>
    ) {
        liveData.postValue(Event.loading())

        this.viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = request.invoke()
                if (response.data != null) {
                    liveData.postValue(Event.success(response.data))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                liveData.postValue(Event.error(null))
            }
        }
    }

    fun getPersons(repository: Repository, liveData: MutableLiveData<List<PersonCache>>) =
        viewModelScope.launch {
            val record = repository.allPersons
            liveData.postValue(record.value)
        }

    fun insert(personCache: PersonCache, repository: Repository) = viewModelScope.launch {
        repository.insert(personCache)
        println("Records in db: " + repository.getCount())
    }

    fun delete(personCache: PersonCache, repository: Repository) = viewModelScope.launch {
        repository.delete(personCache)
    }
}