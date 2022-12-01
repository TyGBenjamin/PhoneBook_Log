package com.example.phonebook.repository

import com.example.phonebook.model.data.local.Contact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface Repository {
    suspend fun getContacts(): List<Contact>
    suspend fun getConatctById(id:Int): Contact
    suspend fun deleteContact(contact: Contact)
    suspend fun insertContact(contact: Contact)
    suspend fun updateContact(contact: Contact)

}
