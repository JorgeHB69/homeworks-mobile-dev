package com.example.reciclerview.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reciclerview.R
import com.example.reciclerview.models.Person
import com.example.reciclerview.repositories.PersonRepository

class PersonDetailViewModel : ViewModel() {
    private val _person: MutableLiveData<Person> = MutableLiveData(null)
    var person: LiveData<Person> = _person

    private val _hasError: MutableLiveData<Boolean> = MutableLiveData(false)
    var hasError: LiveData<Boolean> = _hasError

    fun loadPerson(id: Int) {
        _person.value = PersonRepository.getPersonById(id)
    }

    fun savePerson(person: Person): Boolean {
        try {
            PersonRepository.savePerson(person)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            _hasError.value = true
            return false
        }
    }
}