package com.example.phonebook.model.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo
    val firstName: String,
    @ColumnInfo
    val lastName: String,
    @ColumnInfo
    val phone: List<String>,
    @ColumnInfo
    val email: List<String>,
    @ColumnInfo
    val address: Address = Address("", "", "", "")
)

