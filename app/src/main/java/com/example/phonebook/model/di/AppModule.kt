package com.example.phonebook.model.di

import android.content.Context
import androidx.room.Room
import com.example.phonebook.model.data.local.db.PhoneBookDao
import com.example.phonebook.model.data.local.db.PhoneBookDatabase
import com.example.phonebook.model.repository.RepositoryImpl
import com.example.phonebook.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun providesRoomDB(@ApplicationContext applicationContext: Context): PhoneBookDao = Room.databaseBuilder(
        applicationContext,
        PhoneBookDatabase::class.java, "phonebook-database"
    ).build().phonebookDao()

    @Provides
    fun providesRepo(phoneBookDao: PhoneBookDao) : Repository = RepositoryImpl(phoneBookDao)



//    @Provides
//    @Singleton
//    fun provideDataStore(@ApplicationContext context: Context): DataStoreSource = DataStoreImpl(context)
}
