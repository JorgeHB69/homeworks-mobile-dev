package com.example.reciclerview.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reciclerview.R
import com.example.reciclerview.databinding.ActivityMainBinding
import com.example.reciclerview.ui.adapters.NameAdapter
import com.example.reciclerview.ui.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    val viewmodel: MainViewModel by viewModels()

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

        setupRecyclerView()
        setupViewModelsObservers()
        viewmodel.loadData()
    }

    private fun setupViewModelsObservers() {
        viewmodel.nameList.observe(this) {
            val adapter = binding.lstNames.adapter as NameAdapter
            adapter.setData(it)
            adapter.notifyDataSetChanged()
        }
    }

    private fun setupRecyclerView() {

        val adapter = NameAdapter(arrayOf())
        binding.lstNames.adapter = adapter
        binding.lstNames.layoutManager = LinearLayoutManager(this)
    }
}