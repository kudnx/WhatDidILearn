package com.devventure.whatdidilearn.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.devventure.whatdidilearn.R
import com.devventure.whatdidilearn.databinding.ActivityMainBinding
import com.devventure.whatdidilearn.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recycler = binding.learnedItensRecyclerView
        val adapter = LearnedItemAdapter()
        recycler.adapter = adapter

        val learnedItens = viewModel.learnedItem

        learnedItens.observe(this, {
            adapter.learnedItens = it
        })

        binding.newItemButton.setOnClickListener {
            val intent = Intent(this, LearnedItemRegister::class.java)
            startActivity(intent)
        }


    }
}