package com.example.pma08_shared_preferences

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pma08_shared_preferences.databinding.ActivityMainBinding
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Reference na Firebase Realtime Database
        val database = FirebaseDatabase.getInstance()
        val userRef = database.getReference("users")

        // Uložit data na Firebase kliknutím na tlačítko
        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString()
            val ageString = binding.etAge.text.toString().trim()

            if (ageString.isBlank()) {
                showCustomToast("Hey, fill in the age!", R.drawable.ic_default_triangle)
            } else {
                val age = ageString.toInt()
                val isAdult = binding.cbAdult.isChecked
                if ((age < 18 && isAdult) || (age >= 18 && !isAdult)) {
                    showCustomToast(
                        "That doesn't add up, so I won't save anything.",
                        R.drawable.ic_default_triangle
                    )
                } else {
                    showCustomToast("Alright, saving...", R.drawable.ic_default_triangle)

                    // Uložit data na Firebase
                    val userData = User(name, age, isAdult)
                    userRef.setValue(userData)
                        .addOnSuccessListener {
                            showCustomToast("Data saved successfully!", R.drawable.ic_default_triangle)
                        }
                        .addOnFailureListener {
                            showCustomToast("Failed to save data!", R.drawable.ic_default_triangle)
                        }
                }
            }
        }

        // Načíst data z Firebase kliknutím na tlačítko
        binding.btnLoad.setOnClickListener {
            userRef.get().addOnSuccessListener { dataSnapshot ->
                val userData = dataSnapshot.getValue(User::class.java)
                if (userData != null) {
                    binding.etName.setText(userData.name)
                    binding.etAge.setText(userData.age.toString())
                    binding.cbAdult.isChecked = userData.isAdult
                    showCustomToast("Data loaded successfully!", R.drawable.ic_default_triangle)
                } else {
                    showCustomToast("No data found!", R.drawable.ic_default_triangle)
                }
            }.addOnFailureListener {
                showCustomToast("Failed to load data!", R.drawable.ic_default_triangle)
            }
        }
    }

    private fun showCustomToast(message: String, iconResId: Int) {
        val inflater = layoutInflater
        val layout = inflater.inflate(
            R.layout.custom_toast,
            findViewById(R.id.custom_toast_container)
        )

        val toastIcon: ImageView = layout.findViewById(R.id.toast_icon)
        val toastMessage: TextView = layout.findViewById(R.id.toast_message)

        toastIcon.setImageResource(iconResId)
        toastMessage.text = message

        val toast = Toast(applicationContext)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = layout
        toast.show()
    }
}

// Model dat
data class User(
    val name: String = "",
    val age: Int = 0,
    val isAdult: Boolean = false
)
