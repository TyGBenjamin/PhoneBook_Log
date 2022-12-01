package com.example.phonebook.model.repository

import com.example.phonebook.model.data.local.Contact
import com.example.phonebook.model.data.local.db.PhoneBookDao
import com.example.phonebook.repository.Repository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Repository impl extends form Repository.
 *
 * @property phoneDao
 * @constructor Create empty Repository impl
 */
class RepositoryImpl @Inject constructor(private val phoneDao: PhoneBookDao) : Repository {
    /**
     * Get contacts from database.
     *
     * @return
     */
    override suspend fun getContacts(): List<Contact> = withContext(Dispatchers.IO) {
        return@withContext phoneDao.getContacts()
    }

    /**
     * Get contact by id from database.
     *
     * @param id
     * @return
     */
    override suspend fun getConatctById(id: Int): Contact = withContext(Dispatchers.IO) {
        phoneDao.getContactById(id)
    }

    /**
     * Delete contact from database.
     *
     * @param contact
     */
    override suspend fun deleteContact(contact: Contact) = withContext(Dispatchers.IO) {
        phoneDao.deleteContact(contact)
    }

    /**
     * Insert contact.
     *
     * @param contact
     */
    override suspend fun insertContact(contact: Contact) = withContext(Dispatchers.IO) {
        phoneDao.insertContact(contact)
    }

    /**
     * Update contact.
     *
     * @param contact
     */
    override suspend fun updateContact(contact: Contact) {
        CoroutineScope(Dispatchers.IO).launch {
            phoneDao.updateContact(contact)
        }
    }
}
