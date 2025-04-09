package com.example.calculator.models

class Calculator {
    private var result: String = ""
    private var memory: String = ""
    private var secondaryMemory: Int = 0
    private var prevNumber: Int = 0
    private var currentOperation: OperationType = OperationType.NONE

    fun cleanEntry() {
        result = ""
    }

    fun cleanAll() {
        memory = ""
        result = ""
    }

    fun deleteLast() : String? {
        if (result.isEmpty()) return ""
        result = result.dropLast(1)
        return result
    }

    fun addNumber(number: Int) : String {
        result += number
        result = result.toInt().toString()
        return result
    }

    fun startOperation(operationType: OperationType) {
        currentOperation = operationType
        prevNumber = result.toInt()
        memory = result + when (currentOperation) {
            OperationType.ADDITION -> "+"
            OperationType.SUBTRACTION -> "-"
            OperationType.MULTIPLY -> "x"
            OperationType.DIVISION -> "/"
            OperationType.NONE -> ""
        }
        result = ""
    }

    @Throws(ArithmeticException::class)
    fun solveOperation() : String {
        var currentNumber = 0
        if (!result.isEmpty()) currentNumber = result.toInt()
        val operationResult = when (currentOperation) {
            OperationType.ADDITION -> prevNumber + currentNumber
            OperationType.SUBTRACTION -> prevNumber - currentNumber
            OperationType.MULTIPLY -> prevNumber * currentNumber
            OperationType.DIVISION -> {
                if (currentNumber != 0) {
                    prevNumber / currentNumber
                } else {
                    //Toast.makeText(this, "ERROR: Division by Zero", Toast.LENGTH_SHORT).show()
                    throw ArithmeticException("Division by Zero")
                }
            }

            OperationType.NONE -> currentNumber
        }
        result = operationResult.toString()
        return result
    }

    // Memory Operations
    fun saveResultState() {
        if (!result.isEmpty()) {
            secondaryMemory = result.toInt()
        }
    }

    fun addResultStateToMemory() {
        if (!result.isEmpty()) {
            secondaryMemory += result.toInt()
        }
    }

    fun subtractResultStateToMemory() {
        if (!result.isEmpty()) {
            secondaryMemory -= result.toInt()
        }
    }

    fun cleanMemory() {
        secondaryMemory = 0
    }

    fun callMemory() : String {
        result = secondaryMemory.toString()
        return result
    }
}