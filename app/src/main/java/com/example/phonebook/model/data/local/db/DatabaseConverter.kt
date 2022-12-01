package com.example.phonebook.model.data.local.db

import androidx.room.TypeConverter
import com.example.phonebook.model.data.local.Address

class DatabaseConverter {
    private val separator = ","

    @TypeConverter
    fun convertListToString(email: List<String>): String {
        val stringBuilder = StringBuilder()
        for (item in email) {
            stringBuilder.append(item).append(separator)
        }
        stringBuilder.setLength(stringBuilder.length)
        return stringBuilder.toString()
    }
    @TypeConverter
    fun convertStringToList(string: String): List<String> {
        return string.split(separator)
    }

    @TypeConverter
    fun addressToString(address: Address) = "$address" //Other options are json string, serialized blob
    @TypeConverter
    fun stringToAddress(value: String): Address {
        val streetAddress = value.substringBefore(':')
        val city = value.substringAfter(':')
        val state = value.substringAfter(':')
        val zipcode = value.substringAfter(':')

        return Address(streetAddress, city,state,zipcode)
    }

//    @TypeConverter
//    fun convertListToString(list: List<String>): String {
//        val stringBuilder = StringBuilder()
//        for (item in list) {
//            stringBuilder.append(item).append(separator)
//        }
//        stringBuilder.setLength(stringBuilder.length - separator.length)
//        return stringBuilder.toString()
//    }
//
////    @TypeConverter
////    fun convertEmailToString(email: List<String>): String {
////        val stringBuilder = StringBuilder()
////        for (item in email) {
////            stringBuilder.append(item).append(separator)
////        }
////        stringBuilder.setLength(stringBuilder.length - separator.length)
////        return stringBuilder.toString()
////    }
//
//    @TypeConverter
//    fun convertStringToList(string: String): List<String> {
//        return string.split(separator)
//    }
//
//    @TypeConverter
//    fun addressToString(address: Address) = address.toString()
//         //Other options are json string, serialized blob
//
//    @TypeConverter
//    fun stringToAddress(address: String): Address {
//        val formattedAddress = address.split(':')
//        val streetAddress = formattedAddress.first()
//        println(streetAddress)
//        val city = formattedAddress[1]
//        val state = formattedAddress[2]
//        val zipcode = formattedAddress.last()
//
//        return Address(streetAddress, city, state, zipcode)
//    }
}
