package com.example.calculator.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculator.models.Calculator
import com.example.calculator.models.OperationType

class MainViewModel : ViewModel() {
    private val _result: MutableLiveData<String> = MutableLiveData("")
    val result: LiveData<String> = _result

    private val _memory: MutableLiveData<String> = MutableLiveData("")
    val memory: LiveData<String> = _memory

    private val _showError: MutableLiveData<Boolean> = MutableLiveData(false)
    val showError: LiveData<Boolean> = _showError

    private var calculator = Calculator()

    fun clearEntry() {
        _result.value = ""
        calculator.cleanEntry()
    }

    fun clearAll() {
        _result.value = ""
        _memory.value = ""
        calculator.cleanAll()
    }

    fun deleteLast() {
        _result.value = calculator.deleteLast()
    }

    fun solveOperation() {
        _memory.value = ""
        try {
            _result.value = calculator.solveOperation()
        } catch (e: ArithmeticException) {
            _result.value = ""
            _showError.value = true
        }
    }

    fun startOperation(operationType: OperationType) {
        _memory.value = result.value
        _result.value = ""
        calculator.startOperation(operationType)
    }

    fun addNumber(number: Int) {
        _result.value = calculator.addNumber(number)
    }

    // Memory Operation
    fun saveResultState() {
        calculator.saveResultState()
    }

    fun addResultStateToMemory() {
        calculator.addResultStateToMemory()
    }

    fun subtractResultStateToMemory() {
        calculator.subtractResultStateToMemory()
    }

    fun cleanMemory() {
        calculator.cleanMemory()
    }

    fun callMemory() {
        _result.value = calculator.callMemory()
    }
}