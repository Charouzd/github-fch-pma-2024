package com.example.myapp08_fragmenty

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp08_fragmenty.frags.BlankFragment1
import com.example.myapp08_fragmenty.frags.BlankFragment2

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Výchozí fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, BlankFragment1())
            .commit()

        // Listener na první tlačítko
        findViewById<Button>(R.id.button).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, BlankFragment1())
                .commit()
        }

        // Listener na druhé tlačítko
        findViewById<Button>(R.id.button2).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, BlankFragment2())
                .commit()
        }

    }
}
