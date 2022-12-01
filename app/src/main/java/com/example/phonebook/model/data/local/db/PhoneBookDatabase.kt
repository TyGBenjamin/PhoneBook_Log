package com.example.phonebook.model.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.phonebook.model.data.local.Contact

@Database(entities = [Contact::class], version = 1 )
@TypeConverters(DatabaseConverter::class)
abstract class PhoneBookDatabase : RoomDatabase() {
    abstract fun phonebookDao() : PhoneBookDao
}

