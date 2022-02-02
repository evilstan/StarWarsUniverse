package com.evilstan.starwarsuniverse.ui.favorites

import android.content.Context
import com.evilstan.starwarsuniverse.BaseViewModel
import com.evilstan.starwarsuniverse.domain.cache.PersonCache

class FavoritesViewModel(context: Context) : BaseViewModel(context) {
    val personsFromDb = cachedPersons
}