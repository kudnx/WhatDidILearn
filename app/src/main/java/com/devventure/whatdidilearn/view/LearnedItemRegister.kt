package com.devventure.whatdidilearn.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.devventure.whatdidilearn.R
import com.devventure.whatdidilearn.databinding.ActivityLearnedItemBinding
import com.devventure.whatdidilearn.entities.LearnedItem
import com.devventure.whatdidilearn.entities.UnderstandingLevel
import com.devventure.whatdidilearn.viewmodel.SecondaryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LearnedItemRegister : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLearnedItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val secondaryViewModel : SecondaryViewModel by viewModel()

        binding.btnSave.setOnClickListener{
            if (validateFields(binding))
            {
                secondaryViewModel.addNewItem(getData(binding))
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                Toast.makeText(applicationContext, R.string.validatefields,
                    Toast.LENGTH_SHORT).show()
            }
        }
        
    }

    private fun getData(binding : ActivityLearnedItemBinding): LearnedItem {
        val title = binding.learnedItemName.text.toString()
        val description = binding.learnedItemDescription.text.toString()
        lateinit var learn: UnderstandingLevel

        when(binding.radio.checkedRadioButtonId.toString()) {
            binding.itemLearnedLow.id.toString()       -> learn = UnderstandingLevel.LOW
            binding.itemLearnedMedium.id.toString()    -> learn = UnderstandingLevel.MEDIUM
            binding.itemLearnedHigh.id.toString()      -> learn = UnderstandingLevel.HIGH
        }

        return LearnedItem(name = title, description = description, understandingLevel = learn)
    }

    private fun validateFields(binding : ActivityLearnedItemBinding): Boolean {
        return (binding.learnedItemName.text.isNotEmpty() and
                binding.learnedItemDescription.text.isNotEmpty())
    }
}