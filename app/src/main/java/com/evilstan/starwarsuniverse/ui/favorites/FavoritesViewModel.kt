package com.evilstan.starwarsuniverse.ui.favorites

import android.content.Context
import com.evilstan.starwarsuniverse.ui.core.BaseViewModel

class FavoritesViewModel(context: Context) : BaseViewModel(context) {
    val personsFromDb = cachedPersons
}