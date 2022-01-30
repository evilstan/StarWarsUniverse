package com.evilstan.starwarsuniverse.domain.mapper

import com.evilstan.starwarsuniverse.data.dictionary.FilmCloud
import com.evilstan.starwarsuniverse.domain.cache.FilmCache

class FilmMapper {
    fun map(filmCloud: FilmCloud) = FilmCache(filmCloud.title, filmCloud.episodeId)
}