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

@HiltViewModel
class DashboardViewModel @Inject constructor(private val repository: RepositoryImpl) : ViewModel() {
    private val _contactList: MutableStateFlow<List<Contact>> = MutableStateFlow(emptyList())
    val contactList = _contactList.asStateFlow()

    fun getContacts() =
        viewModelScope.launch {
            _contactList.value = repository.getContacts()
        }

    fun addContactNew(
        contact:Contact
    ) {
        viewModelScope.launch {
            repository.insertContact(contact)
            println("$contact ADDED TO DB")
        }
    }


    fun deleteContact(contact: Contact) = viewModelScope.launch {
        repository.deleteContact(contact)
    }


    companion object {
        const val TAG = "DashboardVIEWMODEL"
    }

}

//     var contactList: Flow<List<Contact>> = flowOf(emptyList())

//    private val _contactListO: Flow<List<Contact>> = flowOf(emptyList())
//    val contactList = repository.getContacts()
//    val contactList = repository.getContacts()

//    fun insertFakeContact() {
//        viewModelScope.launch {
//            repository.insertContact(Note(id =4, title = "NEWNOTE", body = "This is an example of a different note my guy"))
//        }
//    }o
