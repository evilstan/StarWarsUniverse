package com.evilstan.starwarsuniverse.data.dictionary

import com.google.gson.annotations.SerializedName

data class PeopleCloud(@SerializedName("results") var results: ArrayList<PersonCloud>)
