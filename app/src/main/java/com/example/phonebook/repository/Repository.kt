package com.example.phonebook.repository

import com.example.phonebook.model.data.local.Contact

/**
 * Repository abstract interface.
 *
 * @constructor Create empty Repository
 */
interface Repository {
    /**
     * Get contacts from database for UI.
     *
     * @return
     */
    suspend fun getContacts(): List<Contact>

    /**
     * Get conatct by id from database for UI.
     *
     * @param id
     * @return
     */
    suspend fun getConatctById(id:Int): Contact

    /**
     * Delete contact from database for UI.
     *
     * @param contact
     */
    suspend fun deleteContact(contact: Contact)

    /**
     * Insert contact from database for UI.
     *
     * @param contact
     */
    suspend fun insertContact(contact: Contact)

    /**
     * Update contact from database for UI.
     *
     * @param contact
     */
    suspend fun updateContact(contact: Contact)

}
