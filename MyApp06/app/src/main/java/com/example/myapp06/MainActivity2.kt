package com.example.myapp06

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp06.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Nastavení view binding
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Nastavení toolbaru (top baru)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Druhá aktivita"

        // Načtení dat z intentu
        val nickname = intent.getStringExtra("nick")
        binding.twInfo.text = "Data z první aktivity. Jméno: $nickname"

        // Listener na tlačítko pro přechod do třetí aktivity
        binding.btnThird.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            intent.putExtra("extra_data", "Data z druhé aktivity")
            startActivity(intent)
        }

        // Listener na tlačítko zpět
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
