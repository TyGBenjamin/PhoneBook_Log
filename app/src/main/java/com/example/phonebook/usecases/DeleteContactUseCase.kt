package com.example.phonebook.usecases

import com.example.phonebook.model.data.local.Contact
import com.example.phonebook.repository.Repository
import javax.inject.Inject


class DeleteContactUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(contact: Contact){
        repository.deleteContact(contact)
    }
}
