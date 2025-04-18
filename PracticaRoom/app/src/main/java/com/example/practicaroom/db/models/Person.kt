package com.example.practicaroom.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Person(
    var name: String,
    var lastName: String,
    var age: Int,
    var email: String,
    var phone: String,
    var birthDate: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}