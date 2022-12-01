package com.example.phonebook.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phonebook.model.data.local.Address
import com.example.phonebook.model.data.local.Contact
import com.example.phonebook.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AddContactViewModel @Inject constructor(private val repo: Repository) : ViewModel() {
    private val _contactListO: MutableStateFlow<Contact> = MutableStateFlow(
        Contact(
            999, "ty", "gandy",
            emptyList(), emptyList(), Address("", "", "", "")
        )
    )
    val contactListO = _contactListO.asStateFlow()

    fun addContactNew(
     contact:Contact
    ) {
        viewModelScope.launch {
            repo.insertContact(contact)
            println(contact)
            _contactListO.value = contact
        }
    }


fun addContact(
    first: String, last: String,
    phone: List<String>,
    email: List<String>,
    address: Address
) {
    viewModelScope.launch {
        val newContact = Contact(
            firstName = first,
            lastName = last,
            email = email,
            phone = phone,
            address = Address(
                city = address.city,
                state = address.state,
                streetAddress = address.streetAddress,
                zipcode = address.zipcode
            )
        )
        repo.insertContact(newContact)
        println(newContact)
        _contactListO.value = newContact
    }
}

}

