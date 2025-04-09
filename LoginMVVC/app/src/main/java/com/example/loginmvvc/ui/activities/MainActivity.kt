package com.example.loginmvvc.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import com.example.loginmvvc.R
import com.example.loginmvvc.databinding.ActivityMainBinding
import com.example.loginmvvc.ui.viewmodels.MainViewModel
import kotlin.getValue

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

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

        setupEventListeners()
        setupViewModelObservers()
    }

    private fun setupViewModelObservers() {
        viewModel.showError.observe(this) {
            if (it) {
                Toast.makeText(this, "Error: ${viewModel.errorMessage}", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.changeScreen.observe(this) {
            if (it) {
                var intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("username", viewModel.username.value)
                startActivity(intent)
            }
        }
    }

    private fun setupEventListeners() {
        binding.btnLogin.setOnClickListener { viewModel.login() }
        binding.txtInputUsername.doAfterTextChanged {
            viewModel.setUsername(it.toString())
        }
        binding.txtInputPassword.doAfterTextChanged {
            viewModel.setPassword(it.toString())
        }
    }
}