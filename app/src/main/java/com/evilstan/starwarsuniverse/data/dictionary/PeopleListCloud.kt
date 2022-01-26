package com.evilstan.starwarsuniverse.data.dictionary

import com.google.gson.annotations.SerializedName

data class PeopleListCloud(@SerializedName("results") var results: ArrayList<PeopleCloud>)
