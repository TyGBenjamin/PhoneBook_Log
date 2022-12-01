package com.example.phonebook.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phonebook.model.data.local.Contact
import com.example.phonebook.model.repository.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Dashboard view model.
 *
 * @property repository
 * @constructor Create empty Dashboard view model
 */
@HiltViewModel
class DashboardViewModel @Inject constructor(private val repository: RepositoryImpl) : ViewModel() {
    private val _contactList: MutableStateFlow<List<Contact>> = MutableStateFlow(emptyList())
    val contactList = _contactList.asStateFlow()

    /**
     * Get contacts.
     *
     */
    fun getContacts() =
        viewModelScope.launch {
            _contactList.value = repository.getContacts()
        }

    /**
     * Add contact new.
     *
     * @param contact
     */
    fun addContactNew(
        contact:Contact
    ) {
        viewModelScope.launch {
            repository.insertContact(contact)
            println("$contact ADDED TO DB")
        }
    }

    /**
     * Delete contact.
     *
     * @param contact
     */
//    fun deleteContact(contact: Contact) = viewModelScope.launch {
//        repository.deleteContact(contact)
//    }

    /**
     * Companion.
     *
     * @constructor Create empty Companion
     */
    companion object {
        const val TAG = "DashboardVIEWMODEL"
    }
}
