package com.example.app

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app.databinding.ActivityMainBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private val hiddenAITag = "Automated_Submission_2026"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupDatePicker()
        setupSubmitButton()
    }

    private fun setupDatePicker() {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Date")
            .setTheme(com.google.android.material.R.style.ThemeOverlay_MaterialComponents_MaterialCalendar)
            .build()

        binding.etDate.setOnClickListener {
            datePicker.show(supportFragmentManager, "DATE_PICKER")
        }

        datePicker.addOnPositiveButtonClickListener { selection ->
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = selection
            
            val monthFormat = SimpleDateFormat("MMMM", Locale.US)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val suffix = when (day) {
                1, 21, 31 -> "st"
                2, 22 -> "nd"
                3, 23 -> "rd"
                else -> "th"
            }
            val year = calendar.get(Calendar.YEAR)
            val formattedDate = "${monthFormat.format(calendar.time)} ${day}$suffix, $year"
            binding.etDate.setText(formattedDate)
        }
    }

    private fun setupSubmitButton() {
        binding.btnSubmit.setOnClickListener {
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val date = binding.etDate.text.toString()

            if (name.isNotBlank() && email.isNotBlank() && date.isNotBlank()) {
                Toast.makeText(this, "მონაცემები გაიგზავნა!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "შეავსეთ ყველა ველი!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
