package com.example.reciclerview.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reciclerview.R
import com.example.reciclerview.databinding.ActivityPersonListBinding
import com.example.reciclerview.models.Person
import com.example.reciclerview.ui.adapters.PersonAdapter
import com.example.reciclerview.ui.viewmodels.PersonListViewModel

class PersonListActivity : AppCompatActivity(), PersonAdapter.OnPersonClickListener {
    private lateinit var binding : ActivityPersonListBinding
    private val viewModel: PersonListViewModel by viewModels()

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            result.data?.let {
                it.extras?.let { extras ->
                    val personaChanged = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        extras.getSerializable(PARAM_UPDATE_OBJECT, Person::class.java)
                    } else {
                        extras.getSerializable(PARAM_UPDATE_OBJECT) as Person
                    }

                    val sentId = extras.getInt(PARAM_SENT_ID)
                    if (sentId > 0) {
                        dataUpdateWithId()
                    }
                }
            }

        }

    private fun dataUpdateWithId(sentId: Int) {
        val adapter = binding.lstPersons.adapter as PersonAdapter
        adapter.updateById(sentId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPersonListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupRecyclerView()
        setupViewModelObservers()
        setupEventListeners()
        viewModel.loadData()
    }

    private fun setupEventListeners() {
        binding.floatingActionButton.setOnClickListener {
            val intent = PersonDetailActivity.createIntent(this)
            startForResult.launch(intent)
        }
    }

    private fun setupViewModelObservers() {
        viewModel.personList.observe(this) {
            if (it == null || it.isEmpty()) {
                binding.lblEmpty.visibility = View.VISIBLE
                binding.lstPersons.visibility = View.GONE
                return@observe
            }
            binding.lblEmpty.visibility = View.GONE
            binding.lstPersons.visibility = View.VISIBLE
            val adapter = binding.lstPersons.adapter as PersonAdapter
            adapter.setData(it)
        }
    }

    private fun setupRecyclerView() {
        val adapter = PersonAdapter(mutableListOf())
        val dividerItemDecoration = DividerItemDecoration(
            binding.lstPersons.context,
            LinearLayoutManager.VERTICAL
        )
        binding.lstPersons.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@PersonListActivity).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            addItemDecoration(dividerItemDecoration)
        }

        adapter.setOnPersonClickListener(this)
    }

    override fun onPersonClick(person: Person) {
        val id = person.id
        val intent = PersonDetailActivity.detailIntent(this, id)
        startForResult.launch(intent)
    }

    override fun onDeletePerson(person: Person) {
        val index = viewModel.deletePerson(person)
        val adapter = binding.lstPersons.adapter as PersonAdapter
        adapter.removeItem(index)
        if (adapter.itemCount == 0) {
            binding.lblEmpty.visibility = View.VISIBLE
            binding.lstPersons.visibility = View.GONE
        } else {
            binding.lblEmpty.visibility = View.GONE
            binding.lstPersons.visibility = View.VISIBLE
        }
    }

    companion object {
        private const val PARAM_SENT_ID = "sentId"
        private const val PARAM_UPDATE_OBJECT = "updatePerson"

        fun returnIntent(context: Context, id: Int): Intent {
            return Intent(context, PersonListActivity::class.java).apply {
                putExtra(PARAM_SENT_ID, id)
            }
        }
    }
}