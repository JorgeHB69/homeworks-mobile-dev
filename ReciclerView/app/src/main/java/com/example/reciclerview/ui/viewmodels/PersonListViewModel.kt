package com.example.reciclerview.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reciclerview.models.Person
import com.example.reciclerview.repositories.PersonRepository

class PersonListViewModel : ViewModel() {
    private val _personList: MutableLiveData<MutableList<Person>> = MutableLiveData(mutableListOf())
    var personList: LiveData<MutableList<Person>> = _personList

    fun loadData() {
        _personList.value = PersonRepository.getPersons()
    }

    fun deletePerson(person: Person) : Int {
        return PersonRepository.deletePerson(person)
    }
}