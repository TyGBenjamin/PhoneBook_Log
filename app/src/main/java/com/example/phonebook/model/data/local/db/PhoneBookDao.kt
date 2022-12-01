package com.example.phonebook.model.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.example.phonebook.model.data.local.Contact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface PhoneBookDao {
    @Query
        ("SELECT * FROM contacts")
    fun getContacts(): List<Contact>

    @Query
        ("SELECT * FROM contacts WHERE id in (:id)")
    fun getContactById(id: Int) : Contact

    @Update
    fun updateContact(contact: Contact)

    @Insert(onConflict = REPLACE)
    fun insertContact (contact: Contact)

    @Delete
    fun deleteContact(contact: Contact)

}

