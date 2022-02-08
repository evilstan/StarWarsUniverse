package com.evilstan.starwarsuniverse.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class DataConverter {
    @TypeConverter
    fun fromFilmList(films: List<String>?): String? {
        if (films == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<String>?>() {}.type
        return gson.toJson(films, type)
    }

    @TypeConverter
    fun toFilmsList(filmsString: String?): ArrayList<String>? {
        if (filmsString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<String?>?>() {}.type
        return gson.fromJson<ArrayList<String>>(filmsString, type)
    }
}
