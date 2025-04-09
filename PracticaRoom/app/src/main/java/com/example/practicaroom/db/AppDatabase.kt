package com.example.practicaroom.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.practicaroom.db.dao.PersonDao

@Database(entities = [], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
}