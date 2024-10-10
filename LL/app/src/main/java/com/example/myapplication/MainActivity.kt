package com.example.myapplication
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val validMail="getIn@trol.XD"
    val password="12345"


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.buy.setOnClickListener {
            val itemRbid= binding.products.checkedRadioButtonId
            val Rchoosen= findViewById<RadioButton>(itemRbid)

            val del1 = binding.chbtnt1.isChecked
            val del2 = binding.chbtnt2.isChecked
            val del3 = binding.chbtnt3.isChecked
            val del4 = binding.chbtnt4.isChecked
            val p1 = findViewById<RadioButton>(R.id.item1)
            val p2 = findViewById<RadioButton>(R.id.item2)
            val p3 = findViewById<RadioButton>(R.id.item3)

            val  txt= " objednali jste "+ Rchoosen.text + (if(del1)"doprava poštou" else(""))+(if(del2)"doprava AIR DROPEM" else(""))+(if(del3)"doprava EKO" else(""))+(if(del4)"doprava Vzducholodí" else(""))
            binding.output.text=txt

            //Rchoosen.setOnCheckedChangeListener()

        }
        setContentView(binding.root)
        title="Skamovací apka"

        val paybutton = findViewById<Button>(R.id.buy)
        val IVW = findViewById<ImageView>(R.id.pic1)

}
}