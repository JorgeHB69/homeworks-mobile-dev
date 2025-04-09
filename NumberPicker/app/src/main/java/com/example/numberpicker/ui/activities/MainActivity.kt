package com.example.numberpicker.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.numberpicker.R
import com.example.numberpicker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
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
        setUpEventListeners()
    }

    private fun setUpEventListeners() {
        binding.btnShowValue.setOnClickListener{
            val val1 = binding.picker1.counter
            val val2 = binding.picker2.counter
            Toast.makeText(this, "Value 1: $val1\nValue 2: $val2", Toast.LENGTH_SHORT).show()
        }

        binding.picker1.setOnValueChanged {
            Toast.makeText(this, "Value 1: $it", Toast.LENGTH_SHORT).show()
        }

        binding.picker2.setOnValueChanged {
            Toast.makeText(this, "Value 2: $it", Toast.LENGTH_SHORT).show()
        }
    }
}