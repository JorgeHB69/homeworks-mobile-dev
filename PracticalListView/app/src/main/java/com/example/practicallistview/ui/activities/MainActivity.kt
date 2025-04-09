package com.example.practicallistview.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicallistview.R
import com.example.practicallistview.databinding.ActivityMainBinding
import com.example.practicallistview.ui.adapters.NameArrayAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
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

        setUpListView()
    }

    private fun setUpListView() {
        val names = arrayOf(
            "John",
            "Jane",
            "Alice",
            "Bob",
            "Charlie",
            "David",
            "Eve",
            "Frank",
            "Grace",
            "Hank",
            "Ivy",
            "Jack",
            "Kate",
            "Liam",
            "Mia",
            "Noah",
            "Olivia",
            "Peter",
            "Quinn",
            "Ryan",
            "Sara",
            "Tom",
            "Uma",
            "Vince",
            "Wendy",
            "Xander",
            "Yara",
            "Zack"
        )
        val adapter = NameArrayAdapter(this, android.R.layout.simple_list_item_1, names)
        binding.lstNames.adapter = adapter
    }
}