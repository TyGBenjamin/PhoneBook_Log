package com.example.phonebook.model.data.local.db

import androidx.room.TypeConverter
import com.example.phonebook.model.data.local.Address

/**
 * Database converter.
 *
 * @constructor Create empty Database converter
 */
class DatabaseConverter {
    private val separator = ","

    @TypeConverter
            /**
             * Convert list to string.
             *
             * @param email
             * @return
             */
    fun convertListToString(email: List<String>): String {
        val stringBuilder = StringBuilder()
        for (item in email) {
            stringBuilder.append(item).append(separator)
        }
        stringBuilder.setLength(stringBuilder.length)
        return stringBuilder.toString()
    }
    @TypeConverter
            /**
             * Convert string to list.
             *
             * @param string
             * @return
             */
    fun convertStringToList(string: String): List<String> {
        return string.split(separator)
    }

    @TypeConverter
            /**
             * Address to string converter.
             *
             * @param address
             */
    fun addressToString(address: Address) = "$address" //Other options are json string, serialized blob
    @TypeConverter
            /**
             * String to address.
             *
             * @param value
             * @return
             */
    fun stringToAddress(value: String): Address {
        val streetAddress = value.substringBefore(':')
        val city = value.substringAfter(':')
        val state = value.substringAfter(':')
        val zipcode = value.substringAfter(':')

        return Address(streetAddress, city,state,zipcode)
    }
}
