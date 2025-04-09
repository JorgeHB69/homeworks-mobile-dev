package com.example.reciclerview.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.reciclerview.R
import com.example.reciclerview.databinding.ActivityPersonDetailBinding
import com.example.reciclerview.models.Person
import com.example.reciclerview.ui.viewmodels.PersonDetailViewModel

class PersonDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPersonDetailBinding
    private val viewModel: PersonDetailViewModel by viewModels()
    private var id: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPersonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupViewModelObservers()
        setupEventListeners()

        id = intent.getIntExtra(PARAM_ID, 0)
        if (id == 0) { return }
        viewModel.loadPerson(id)
    }

    private fun setupEventListeners() {
        binding.btnSave.setOnClickListener { doSave() }
        binding.btnCancel.setOnClickListener { finish() }
    }

    private fun doSave() {
        var person = viewModel.person.value ?: Person()
        person.apply {
            name = binding.txtName.text.toString()
            lastName = binding.txtLastName.text.toString()
            age = binding.txtAge.text.toString().toInt()
            email = binding.txtEmail.text.toString()
            phone = binding.txtPhone.text.toString()
        }
        val saved = viewModel.savePerson(person)
        if (saved) {
            val resultIntent = PersonListActivity.returnIntent(this, id)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }

    private fun setupViewModelObservers() {
        viewModel.person.observe(this) {
            if (it == null) return@observe
            binding.txtName.setText(it.name)
            binding.txtLastName.setText(it.lastName)
            binding.txtAge.setText(it.age.toString())
            binding.txtPhone.setText(it.phone)
            binding.txtEmail.setText(it.email)
        }

        viewModel.hasError.observe(this) {
            if (it) {
                Toast.makeText(this, this.getString(R.string.error_save_person), Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        fun detailIntent(context: Context, id: Int): Intent {
            val intent = Intent(context, PersonDetailActivity::class.java)
            intent.putExtra(PARAM_ID, id)
            return intent
        }

        fun createIntent(context: Context): Intent {
            return Intent(context, PersonDetailActivity::class.java)
        }

        private const val PARAM_ID = "id"
    }
}