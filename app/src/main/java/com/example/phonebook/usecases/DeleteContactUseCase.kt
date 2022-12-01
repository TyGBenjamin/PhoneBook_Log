package com.example.phonebook.usecases

import com.example.phonebook.model.data.local.Contact
import com.example.phonebook.repository.Repository
import javax.inject.Inject

/**
 * Delete contact use case for repo.
 *
 * @property repository
 * @constructor Create empty Delete contact use case
 */
class DeleteContactUseCase @Inject constructor(private val repository: Repository) {
    /**
     * Invoke.
     *
     * @param contact
     */
    suspend operator fun invoke(contact: Contact) {
        repository.deleteContact(contact)
    }
}
