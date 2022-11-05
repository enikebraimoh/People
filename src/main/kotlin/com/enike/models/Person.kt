package com.enike.models

import kotlinx.serialization.Serializable

val peopleStorage = mutableListOf<Person>()

@Serializable
data class Person(val name: String, val age: Int, val occupation: String, val maritalStatus: String)