package com.example.roomdbs.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomdbs.db.daos.PersonDao
import com.example.roomdbs.db.daos.PetDao
import com.example.roomdbs.db.models.Person
import com.example.roomdbs.db.models.Pet

@Database(
    entities = [Person::class, Pet::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
    abstract fun petDao(): PetDao
}
