package com.evilstan.starwarsuniverse.data.dictionary

import com.evilstan.starwarsuniverse.data.dictionary.cache.FilmCache

class FilmMapper {
    fun map(filmCloud: FilmCloud) = FilmCache(filmCloud.title, filmCloud.episodeId)
}