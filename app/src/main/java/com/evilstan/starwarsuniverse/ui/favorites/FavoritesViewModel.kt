package com.evilstan.starwarsuniverse.ui.favorites

import android.app.Application
import android.content.Context
import com.evilstan.starwarsuniverse.ui.core.BaseViewModel

class FavoritesViewModel : BaseViewModel() {
    val personsFromDb = cachedPersons
}