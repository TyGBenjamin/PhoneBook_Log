package com.example.phonebook.model.repository

import com.example.phonebook.model.data.local.Contact
import com.example.phonebook.model.data.local.db.PhoneBookDao
import com.example.phonebook.repository.Repository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RepositoryImpl @Inject constructor(private val phoneDao: PhoneBookDao) : Repository {
    override suspend fun getContacts(): List<Contact> = withContext(Dispatchers.IO) {
        return@withContext phoneDao.getContacts()
    }

    override suspend fun getConatctById(id: Int): Contact = withContext(Dispatchers.IO) {
        phoneDao.getContactById(id)
    }

    override suspend fun deleteContact(contact: Contact) = withContext(Dispatchers.IO) {
        phoneDao.deleteContact(contact)
    }

    override suspend fun insertContact(contact: Contact) = withContext(Dispatchers.IO) {
        phoneDao.insertContact(contact)
    }

    override suspend fun updateContact(contact: Contact) {
        CoroutineScope(Dispatchers.IO).launch {
            phoneDao.updateContact(contact)
        }
    }
}
