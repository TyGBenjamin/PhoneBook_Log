package com.example.phonebook.model.data.local

/**
 * Address data class holder.
 *
 * @property streetAddress
 * @property city
 * @property state
 * @property zipcode
 * @constructor Create empty Address
 */
data class Address(
    val streetAddress: String,
    val city: String,
    val state: String,
    val zipcode: String
) {
    /**
     * To string.
     *
     * @return
     */
    override fun toString(): String {
        return "$streetAddress:$city:$state:$zipcode"
    }
}

