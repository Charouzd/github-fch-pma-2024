package com.example.myapp06

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp06.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Nastavení view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Nastavení toolbaru (top baru)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "první aktivita"

        // Listener na tlačítko pro přechod do druhé aktivity
        binding.btnSecond.setOnClickListener {
            val nickname = binding.etNickname.text.toString() // získáme text z edit text pole
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("nick", nickname)
            startActivity(intent)
        }
    }
}
