package com.example.myapp07

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp07.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicializace ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnShowToast.setOnClickListener {

            val toast = Toast.makeText(this, "Fireball!", Toast.LENGTH_SHORT)

            toast.show()
        }

        // Nastavení akce pro tlačítko Zobrazit SNACKBAR

        binding.btnShowSnackbar.setOnClickListener {

            Snackbar.make(binding.root, "buff +2 charisma and agility",Snackbar.LENGTH_SHORT)

                .setDuration(7000)
                .setBackgroundTint(Color.parseColor("#FC35BA"))
                .setTextColor(Color.BLACK)
                .setActionTextColor(Color.WHITE)

                .setAction("dispell") {
                    Toast.makeText(this, "Dispelling a buff", Toast.LENGTH_SHORT).show()
                }

                .show()
        }

    }
}