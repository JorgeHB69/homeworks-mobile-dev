package com.example.reciclerview.repositories

import com.example.reciclerview.models.Person

object PersonRepository {
    private val personList = mutableListOf(
        Person(1, "Alice", "Smith", 29, "+591 71234567", "alice.smith@gmail.com"),
        Person(2, "Carlos", "Gómez", 34, "+591 78451236", "carlos.gomez@hotmail.com"),
        Person(3, "Lucía", "Fernández", 22, "+591 76549812", "lucia.fernandez@yahoo.com"),
        Person(4, "David", "Ramírez", 41, "+591 70123456", "david.ramirez@gmail.com"),
        Person(5, "Sofía", "Martínez", 27, "+591 79012345", "sofia.martinez@outlook.com"),
        Person(6, "Andrés", "López", 36, "+591 78543621", "andres.lopez@gmail.com"),
        Person(7, "Elena", "Vargas", 31, "+591 76981234", "elena.vargas@hotmail.com"),
        Person(8, "Miguel", "Torres", 45, "+591 74569321", "miguel.torres@yahoo.com"),
        Person(9, "Paula", "Castillo", 24, "+591 77894561", "paula.castillo@gmail.com"),
        Person(10, "Javier", "Rojas", 38, "+591 73456981", "javier.rojas@outlook.com"),
        Person(11, "Laura", "Mendoza", 26, "+591 76983452", "laura.mendoza@hotmail.com"),
        Person(12, "Fernando", "Suárez", 39, "+591 70192384", "fernando.suarez@gmail.com"),
        Person(13, "Isabel", "Morales", 33, "+591 77654321", "isabel.morales@yahoo.com"),
        Person(14, "Diego", "Peña", 30, "+591 74561239", "diego.pena@gmail.com"),
        Person(15, "Valeria", "Ortega", 28, "+591 76812345", "valeria.ortega@hotmail.com"),
        Person(16, "Esteban", "Herrera", 35, "+591 78945123", "esteban.herrera@yahoo.com"),
        Person(17, "Camila", "Navarro", 23, "+591 75689134", "camila.navarro@gmail.com"),
        Person(18, "Ricardo", "Salazar", 42, "+591 73219845", "ricardo.salazar@hotmail.com"),
        Person(19, "Daniela", "Cruz", 32, "+591 74128956", "daniela.cruz@gmail.com"),
        Person(20, "Hugo", "Delgado", 40, "+591 70985631", "hugo.delgado@outlook.com")
    )
    fun getPersons(): MutableList<Person> {
        return personList
    }

    fun getPersonById(id: Int): Person? {
        return personList.find { it.id == id }
    }

    fun savePerson(person: Person) {
        if (person.id == 0) {
            insertPerson(person)
        } else {
            updatePerson(person)
        }
    }

    private fun updatePerson(person: Person) {
        val position = personList.indexOfFirst { it.id == person.id }
        if (position == -1) return
        personList[position] = person
    }

    private fun insertPerson(person: Person) {
        val lastId = personList.maxOfOrNull { it.id } ?: 0
        person.id = lastId + 1
        personList.add(person)
    }

    fun deletePerson(person: Person): Int {
        val position = personList.indexOfFirst { it.id == person.id }
        if (position == -1) return -1
        personList.removeAt(position)
        return position
    }
}