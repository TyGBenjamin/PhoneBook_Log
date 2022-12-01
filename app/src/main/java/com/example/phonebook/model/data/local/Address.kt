package com.example.phonebook.model.data.local

data class Address(
    val streetAddress: String,
    val city: String,
    val state: String,
    val zipcode: String
) {
    override fun toString(): String {
        return "$streetAddress:$city:$state:$zipcode"
    }

}

fun Address.convert (): List<String>{
    return listOf(streetAddress, city, state, zipcode)

}
