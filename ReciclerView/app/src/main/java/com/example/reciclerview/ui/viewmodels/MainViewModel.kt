package com.example.reciclerview.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reciclerview.repositories.NameListRepository

class MainViewModel : ViewModel() {
    private val _nameList: MutableLiveData<Array<String>> = MutableLiveData(arrayOf())
    var nameList: LiveData<Array<String>> = _nameList

    fun loadData() {
        _nameList.value = NameListRepository.getNames()
    }
}