package com.evilstan.starwarsuniverse.data.dictionary

import com.evilstan.starwarsuniverse.data.dictionary.cache.HomeWorldCache

class HomeWorldMapper {
    fun map(homeWorldCloud: HomeWorldCloud) = HomeWorldCache(homeWorldCloud.name)
}