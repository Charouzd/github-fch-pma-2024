package com.example.myapplication
import android.widget.Toast
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    val validMail="getIn@trol.XD"
    val password="12345"

    // Handler a Runnable pro cyklické zobrazování práce
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var workRunnable: Runnable
    private var isWorking = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Najdeme si prvky z XML layoutu
        val uname = findViewById<EditText>(R.id.uname)
        val passwd = findViewById<EditText>(R.id.passwd)
        val loginButton = findViewById<Button>(R.id.login)
        val logoutButton = findViewById<Button>(R.id.logout)
        val output = findViewById<TextView>(R.id.output)
        val workButtons = findViewById<LinearLayout>(R.id.workButtons)
        val startWorkButton = findViewById<Button>(R.id.startWork)
        val stopWorkButton = findViewById<Button>(R.id.stopWork)
        // Nastavení funkčnosti tlačítka Přihlásit
        loginButton.setOnClickListener {
            val enteredEmail = uname.text.toString()
            val enteredPassword = passwd.text.toString()

            // Kontrola zadaného emailu a hesla
            if (enteredEmail == validMail && enteredPassword == password) {
                // Úspěšné přihlášení
                Toast.makeText(this, "Přihlášení úspěšné!", Toast.LENGTH_SHORT).show()

                // Skrytí tlačítka Přihlásit a zobrazení tlačítka Odhlásit
                loginButton.visibility = View.GONE
                logoutButton.visibility = View.VISIBLE
                workButtons.visibility = View.VISIBLE
                // Uzamknutí textových polí
                uname.isEnabled = false
                passwd.isEnabled = false

                // Zobrazení informace
                output.visibility = View.VISIBLE
                output.text = "Přihlášen: $enteredEmail"
            }  else {
                // Zobrazení dialogu při neúspěšném přihlášení
                showAlertDialog("Chyba", "Neplatné přihlašovací údaje. Zkuste to prosím znovu.")
            }
        }

        // Nastavení funkčnosti tlačítka Odhlásit
        logoutButton.setOnClickListener {
            // Odhlášení
            Toast.makeText(this, "Odhlášení úspěšné!", Toast.LENGTH_SHORT).show()

            // Zobrazení tlačítka Přihlásit a skrytí tlačítka Odhlásit
            loginButton.visibility = View.VISIBLE
            logoutButton.visibility = View.GONE
            // Skrytí pracovních tlačítek
            workButtons.visibility = View.GONE
            // Odemknutí textových polí
            uname.isEnabled = true
            passwd.isEnabled = true

            // Vyprázdnění textových polí
            uname.text.clear()
            passwd.text.clear()

            // Skrytí informace o přihlášení
            output.visibility = View.GONE

        }
        // Nastavení tlačítka "Začít práci"
        startWorkButton.setOnClickListener {
            startWork(output)
        }

        // Nastavení tlačítka "Ukončit práci"
        stopWorkButton.setOnClickListener {
            stopWork(output)
        }
    }
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

    // Funkce pro spuštění cyklické práce
    private fun startWork(output: TextView) {
        if (!isWorking) {
            isWorking = true
            workRunnable = object : Runnable {
                override fun run() {
                    if (isWorking) {
                        // Nastavíme text na "Probíhá práce"
                        output.text = "Probíhá práce"

                        // Po 3 sekundách vymažeme text na 1 sekundu
                        handler.postDelayed({
                            if (isWorking) {
                                output.text = ""  // Vymažeme text pouze, pokud stále probíhá práce
                                handler.postDelayed(this, 1000)  // Po 1 sekundě znovu spustí cyklus
                            }
                        }, 1500)  // Zobrazí text na 3 sekundy
                    }
                }
            }
            handler.post(workRunnable)
        }
    }

    // Funkce pro zastavení práce
    private fun stopWork(output: TextView? = null) {
        isWorking = false
        handler.removeCallbacks(workRunnable)  // Zastavení cyklu ihned
        output?.let {
            it.text = "Práce ukončena"  // Nastavíme text na "Práce ukončena"
        }


}
    // Funkce pro zobrazení AlertDialog
    private fun showAlertDialog(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)

        // Nastavení tlačítka OK
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()  // Zavřít dialog po kliknutí na OK
        }

        // Vytvoření a zobrazení dialogu
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}