package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val name = findViewById<EditText>(R.id.input1);
        val lastname = findViewById<EditText>(R.id.input2);
        val addr = findViewById<EditText>(R.id.input3);
        val age = findViewById<EditText>(R.id.input4);
        val info = findViewById<TextView>(R.id.output);
        val conf = findViewById<Button>(R.id.btn1);
        val del = findViewById<Button>(R.id.btn2);

        conf.setOnClickListener{
            val n= name.text.toString()
            val ftext="Já se jmenuji " +name.text.toString()+" "+lastname.text.toString()+ " a je mi "+age.text.toString()+". Moje Bydliště je " +addr.text.toString() +"rád vás poznávám";
            info.text=ftext;
        }
        del.setOnClickListener {
            name.text.clear()
            lastname.text.clear()
            age.text.clear()
            addr.text.clear()
            info.text=""


        }
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
    }
}