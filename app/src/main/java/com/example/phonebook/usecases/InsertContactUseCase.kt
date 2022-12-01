package com.example.phonebook.usecases

import com.example.phonebook.model.data.local.Address
import com.example.phonebook.model.data.local.Contact
import com.example.phonebook.repository.Repository
import javax.inject.Inject

class InsertContactUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(firstName: String, lastName: String, phone: List<String>, email: List<String>, address: Address) {
        repository.insertContact(Contact(firstName = firstName, lastName = lastName, phone = phone, email = email, address = address))

    }
}
