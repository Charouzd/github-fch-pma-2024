package com.example.myapp08b

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializace ListFragmentu vlevo
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.listFragmentContainer, ListFragment())
                .commit()
        }
    }

    // Funkce pro předání dat z ListFragment do DetailFragment
    fun onRaceSelected(imageResId: Int, detailText: String) {
        val detailFragment = DetailFragment()

        // Předání argumentů do DetailFragment
        val args = Bundle().apply {
            putInt("imageResId", imageResId)
            putString("detailText", detailText)
        }
        detailFragment.arguments = args

        // Nahrazení pravého panelu fragmentem DetailFragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.detailFragmentContainer, detailFragment)
            .commit()
    }
}
