package com.example.calculator.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import com.example.calculator.models.OperationType
import com.example.calculator.R
import com.example.calculator.databinding.ActivityMainBinding
import com.example.calculator.ui.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setUpViewModelsObservers()
        setupEventListeners()
    }

    private fun setUpViewModelsObservers() {
        viewModel.result.observe(this) {
            if (it.isEmpty()) {
                binding.textView.text = "0"
            } else {
                binding.textView.text = it
            }
        }

        viewModel.memory.observe(this) {
            binding.txtMemoryOperation.text = it
        }

        viewModel.showError.observe(this) {
            if (it) {
                Toast.makeText(this, "Error, division by Zero", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupEventListeners() {
        binding.btn0.setOnClickListener { viewModel.addNumber(0) }
        binding.btn1.setOnClickListener { viewModel.addNumber(1) }
        binding.btn2.setOnClickListener { viewModel.addNumber(2) }
        binding.btn3.setOnClickListener { viewModel.addNumber(3) }
        binding.btn4.setOnClickListener { viewModel.addNumber(4) }
        binding.btn5.setOnClickListener { viewModel.addNumber(5) }
        binding.btn6.setOnClickListener { viewModel.addNumber(6) }
        binding.btn7.setOnClickListener { viewModel.addNumber(7) }
        binding.btn8.setOnClickListener { viewModel.addNumber(8) }
        binding.btn9.setOnClickListener { viewModel.addNumber(9) }

        binding.btnActionPlus.setOnClickListener { viewModel.startOperation(OperationType.ADDITION) }
        binding.btnActionSubstraction.setOnClickListener { viewModel.startOperation(OperationType.SUBTRACTION) }
        binding.btnActionMultiply.setOnClickListener { viewModel.startOperation(OperationType.MULTIPLY) }
        binding.btnActionDivision.setOnClickListener { viewModel.startOperation(OperationType.DIVISION) }
        binding.btnActionGetResult.setOnClickListener { viewModel.solveOperation() }

        binding.btnDelete.setOnClickListener { viewModel.deleteLast() }
        binding.btnClean.setOnClickListener { viewModel.clearAll() }
        binding.btnCleanEntry.setOnClickListener { viewModel.clearEntry() }

        binding.btnMemorySave.setOnClickListener { viewModel.saveResultState() }
        binding.btnMemoryPlus.setOnClickListener { viewModel.addResultStateToMemory() }
        binding.btnMemorySubstraction.setOnClickListener { viewModel.subtractResultStateToMemory() }
        binding.btnMemoryRecall.setOnClickListener { viewModel.callMemory() }
        binding.btnMemoryClean.setOnClickListener { viewModel.cleanMemory() }
    }
}