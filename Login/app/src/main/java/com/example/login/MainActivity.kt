package com.example.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var txtName: EditText
    private lateinit var txtPassword: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtName = findViewById(R.id.txtName)
        txtPassword = findViewById(R.id.txtPassword)
        loginButton = findViewById(R.id.button)
        setEventListeners(this)
    }

    private fun setEventListeners(context: Context) {
        loginButton.setOnClickListener {
            val name = txtName.text.toString()
            val password = txtPassword.text.toString()

            if (name == "admin" && password == "password") {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("user", name)
                startActivity(intent)
            } else {
                Toast.makeText(context, "The user or password is incorrect", Toast.LENGTH_SHORT).show()
            }
        }
    }
}