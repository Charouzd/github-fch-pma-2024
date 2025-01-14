package com.example.myapp015_vanocniaplikace


import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapp015_vanocniaplikace.databinding.ActivityMainBinding
import com.example.myapp015_vanocniaplikace.fragments.Koledy
import com.example.myapp015_vanocniaplikace.fragments.Mix
import com.example.myapp015_vanocniaplikace.fragments.Moderni
import com.example.myapp015_vanocniaplikace.fragments.Intro

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding // View Binding pro hlavní aktivitu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializace View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        // Nastavení výchozího fragmentu - Intro
        if (savedInstanceState == null) {
            openFragment(Intro())
        }

        // Nastavení listenerů pro tlačítka
        binding.koledy.setOnClickListener {
            openFragment(Koledy())
        }

        binding.modern.setOnClickListener {
            openFragment(Moderni())
        }

        binding.mix.setOnClickListener {
            openFragment(Mix())
        }
    }

    // Funkce pro výměnu fragmentů
    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.musicPlayer, fragment) // FrameLayout v XML slouží jako kontejner
            .addToBackStack(null) // Umožňuje navigaci zpět
            .commit()
    }
}
