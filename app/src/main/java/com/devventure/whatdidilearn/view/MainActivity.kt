package com.devventure.whatdidilearn.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.devventure.whatdidilearn.R
import com.devventure.whatdidilearn.WhatDidILearnApplication
import com.devventure.whatdidilearn.databinding.ActivityMainBinding
import com.devventure.whatdidilearn.entities.LearnedItem
import com.devventure.whatdidilearn.entities.UnderstandingLevel
import com.devventure.whatdidilearn.viewmodel.MainViewModel
import com.devventure.whatdidilearn.viewmodel.SecondaryViewModel
import com.devventure.whatdidilearn.viewmodel.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()
    private val secondaryViewModel : SecondaryViewModel by viewModel()
    lateinit var understandingLevel : UnderstandingLevel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recycler = binding.learnedItensRecyclerView
        val adapter = LearnedItemAdapter()
        recycler.adapter = adapter

        /*val repository = (application as WhatDidILearnApplication).repository

        val viewModelFactory = ViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)*/

        val learnedItens = viewModel.learnedItem

        learnedItens.observe(this, Observer {
            adapter.learnedItens = it
        })

        binding.newItemButton.setOnClickListener {
            setContentView(R.layout.fragment_learned_item_register)
        }


    }

    fun saveItem(view: View) {
        val itemNome = findViewById<View>(R.id.learned_item_name) as EditText
        val itemDescription = findViewById<View>(R.id.learned_item_description) as EditText
        val radioGroup = findViewById<RadioGroup>(R.id.radio)


        when (radioGroup.checkedRadioButtonId.toString()) {
            R.id.item_learned_low.toString() -> understandingLevel = UnderstandingLevel.LOW
            R.id.item_learned_medium.toString() -> understandingLevel = UnderstandingLevel.MEDIUM
            R.id.item_learned_high.toString() -> understandingLevel = UnderstandingLevel.HIGH
        }

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                secondaryViewModel.addNewItem(
                    LearnedItem(
                        name = itemNome.text.toString(),
                        description = itemDescription.text.toString(),
                        understandingLevel = understandingLevel
                    )
                )
            }
        }
    }
}