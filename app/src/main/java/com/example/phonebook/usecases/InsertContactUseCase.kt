package com.example.phonebook.usecases

import com.example.phonebook.model.data.local.Address
import com.example.phonebook.model.data.local.Contact
import com.example.phonebook.repository.Repository
import javax.inject.Inject

/**
 * Insert contact use case.
 *
 * @property repository
 * @constructor Create empty Insert contact use case
 */
class InsertContactUseCase @Inject constructor(private val repository: Repository) {
    /**
     * Invoke.
     *
     * @param firstName
     * @param lastName
     * @param phone
     * @param email
     * @param address
     */
    suspend operator fun invoke(
        firstName: String,
        lastName: String,
        phone: List<String>,
        email: List<String>,
        address: Address
    ) {
        repository.insertContact(
            Contact(
                firstName = firstName,
                lastName = lastName,
                phone = phone,
                email = email,
                address = address
            )
        )
    }
}
