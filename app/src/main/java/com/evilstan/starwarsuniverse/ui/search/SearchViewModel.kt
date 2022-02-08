package com.evilstan.starwarsuniverse.ui.search

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.evilstan.starwarsuniverse.cloud.core.NetModule
import com.evilstan.starwarsuniverse.cloud.core.StarWarsApi
import com.evilstan.starwarsuniverse.cloud.dictionary.FilmCloud
import com.evilstan.starwarsuniverse.cloud.dictionary.PersonCloud
import com.evilstan.starwarsuniverse.cloud.dictionary.ResponseWrapper
import com.evilstan.starwarsuniverse.domain.models.CharacterCache
import com.evilstan.starwarsuniverse.domain.models.Character
import com.evilstan.starwarsuniverse.domain.models.CharacterUi
import com.evilstan.starwarsuniverse.ui.core.BaseViewModel
import com.evilstan.starwarsuniverse.ui.core.ErrorMessage
import com.evilstan.starwarsuniverse.ui.core.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class SearchViewModel: BaseViewModel() {

    private val debounce = 500L
    val mappedPersons = MutableLiveData<Event<List<CharacterCache>>>()
    val filmedPerson = MutableLiveData<Character>()

    private var starWarsApi: StarWarsApi = NetModule().service(StarWarsApi::class.java)
    private var searchJob: Job? = null

    fun search(name: String) {
        if (name.isEmpty() || name.isBlank()) {
            mappedPersons.postValue(Event.success(arrayListOf()))
            searchJob?.cancel()
            return
        }
        searchJob?.cancel()
        requestPersons(name)
    }

    private fun requestPersons(name: String) {
        searchJob = this.viewModelScope.launch(Dispatchers.IO) {
            delay(debounce)
            mappedPersons.postValue(Event.loading())

            try {
                val request: suspend () -> ResponseWrapper<ArrayList<PersonCloud.Base>> =
                    { starWarsApi.search(name) }

                val response = request.invoke()
                if (response.data != null && response.data.isNotEmpty()) {
                    mappedPersons.postValue(Event.success(map(response.data)))
                } else {
                    mappedPersons.postValue(Event.error(ErrorMessage.ErrorCode.CHARACTER_NOT_FOUND))
                }

            } catch (e: UnknownHostException) {
                e.printStackTrace()
                mappedPersons.postValue(Event.error(ErrorMessage.ErrorCode.NO_INTERNET))
            } catch (e: Exception) {
                e.printStackTrace()
                mappedPersons.postValue(Event.error(ErrorMessage.ErrorCode.UNKNOWN_ERROR))
            }
        }
    }

    private suspend fun map(response: List<PersonCloud>): List<CharacterCache> {
        val result = response.map { it.map() }
        result.forEach { it.favorite = dbContains(it.name) }
        return result
    }


    fun addFilms(character: Character) {
        mappedPersons.postValue(Event.loading())

        this.viewModelScope.launch(Dispatchers.IO) {
            character.films = character.films.map { (requestFilms(it).toString()) }
            filmedPerson.postValue(character)
        }
    }


    private suspend fun requestFilms(ulr: String): FilmCloud {
        var film = FilmCloud.Base()

            try {
                val request: suspend () -> ResponseWrapper<FilmCloud> =
                    { starWarsApi.getFilm(ulr) }
                val response = request.invoke()
                if (response.title != null) {
                    println(response)
                    film = FilmCloud.Base(response.title, response.episode!!)
                    mappedPersons.postValue(Event.success(null))
                }
            } catch (e: UnknownHostException) {
                e.printStackTrace()
                mappedPersons.postValue(Event.error(ErrorMessage.ErrorCode.NO_INTERNET))
            } catch (e: Exception) {
                e.printStackTrace()
                mappedPersons.postValue(Event.error(ErrorMessage.ErrorCode.UNKNOWN_ERROR))
            }
        return film
    }
}