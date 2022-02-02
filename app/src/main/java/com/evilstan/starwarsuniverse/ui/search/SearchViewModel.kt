package com.evilstan.starwarsuniverse.ui.search

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.evilstan.starwarsuniverse.BaseViewModel
import com.evilstan.starwarsuniverse.data.core.NetModule
import com.evilstan.starwarsuniverse.data.core.StarWarsApi
import com.evilstan.starwarsuniverse.data.dictionary.FilmCloud
import com.evilstan.starwarsuniverse.data.dictionary.PersonCloud
import com.evilstan.starwarsuniverse.data.dictionary.ResponseWrapper
import com.evilstan.starwarsuniverse.domain.cache.PersonCache
import com.evilstan.starwarsuniverse.domain.mapper.FilmMapper
import com.evilstan.starwarsuniverse.domain.mapper.PersonMapper
import com.evilstan.starwarsuniverse.ui.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(context: Context) : BaseViewModel(context) {

    val personsFromCloud = MutableLiveData<Event<ArrayList<PersonCloud>>>()
    val personsMapped = MutableLiveData<Event<ArrayList<PersonCache>>>()

    private var starWarsApi: StarWarsApi = NetModule().service(StarWarsApi::class.java)


    fun search(name: String) {
        personsMapped.postValue(Event.loading())
        val mapper = PersonMapper()

        this.viewModelScope.launch(Dispatchers.IO) {
            try {
                val request: suspend () -> ResponseWrapper<ArrayList<PersonCloud>> =
                    { starWarsApi.search(name) }

                val response = request.invoke()
                if (response.data != null) {
                    val personCacheList = arrayListOf<PersonCache>()

                    for (personCloud in response.data) {
                        val personCache = mapper.map(personCloud)
                        personCache.favorite = dbContains(personCache.name)
                        personCacheList.add(personCache)
                    }

                    personsMapped.postValue(Event.success(personCacheList))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                personsMapped.postValue(Event.error())
            }
        }
    }

    var film = FilmCloud("", 0)

    private suspend fun getFilms(ulr: String): FilmCloud {
        personsMapped.postValue(Event.loading())

        this.viewModelScope.launch(Dispatchers.IO) {
            try {
                val request: suspend () -> ResponseWrapper<FilmCloud> =
                    { starWarsApi.getFilm(ulr) }
                val response = request.invoke()
                if (response.title != null) {
                    film = FilmCloud(response.title, response.episode!!)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                personsMapped.postValue(Event.error())
            }
        }.join()
        return film
    }

    val filmedPerson = MutableLiveData<PersonCache>()

    fun addFilm(personCache: PersonCache) {

        val films = arrayListOf<String>()
        val filmMapper = FilmMapper()

        viewModelScope.launch {
            for (episode in personCache.films) {
                films.add(filmMapper.map(getFilms(episode)))
            }
            personCache.films = films
            filmedPerson.postValue(personCache)
        }
    }
}