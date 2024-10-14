package com.example.myapp06

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp06.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {

    private lateinit var binding: ActivityMain3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Nastavení view binding
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Nastavení toolbaru (top baru)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Třetí aktivita"

        // Načtení dat z intentu
        val extraData = intent.getStringExtra("extra_data")
        binding.twExtraData.text = "Data z druhé aktivity: $extraData"
    }
}
