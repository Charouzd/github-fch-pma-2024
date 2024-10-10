package com.example.myapp05jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExample()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposeExample() {

    // Stavy pro jednotlivé textové vstupy
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var place by remember { mutableStateOf("") }
    var profession by remember { mutableStateOf("") }
    var childrenCount by remember { mutableStateOf("") }
    var resultText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Moje Aplikace", color = Color.White) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.DarkGray
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Textová pole pro vstupy
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Jméno") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = surname,
                onValueChange = { surname = it },
                label = { Text("Příjmení") },
                modifier = Modifier.fillMaxWidth()
            )

            // Validace věku (mezi 1 a 150)
            OutlinedTextField(
                value = age,
                onValueChange = {
                    if (it.all { char -> char.isDigit() } && it.toIntOrNull()?.let { it in 1..150 } == true) {
                        age = it
                    }
                },
                label = { Text("Věk (mezi 1 a 150)") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = place,
                onValueChange = { place = it },
                label = { Text("Bydliště") },
                modifier = Modifier.fillMaxWidth()
            )

            // Nové textové pole pro profesi
            OutlinedTextField(
                value = profession,
                onValueChange = { profession = it },
                label = { Text("Profese") },
                modifier = Modifier.fillMaxWidth()
            )

            // Validace počtu dětí (mezi 0 a 25)
            OutlinedTextField(
                value = childrenCount,
                onValueChange = {
                    if (it.all { char -> char.isDigit() } && it.toIntOrNull()?.let { it in 0..25 } == true) {
                        childrenCount = it
                    }
                },
                label = { Text("Počet dětí (mezi 0 a 25)") },
                modifier = Modifier.fillMaxWidth()
            )

            // Tlačítka Odeslat a Vymazat
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = {
                        resultText =
                            "Jmenuji se $name $surname. Je mi $age let, moje bydliště je $place, pracuji jako $profession a mám $childrenCount dětí."
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Odeslat")
                }

                Button(
                    onClick = {
                        name = ""
                        surname = ""
                        age = ""
                        place = ""
                        profession = ""
                        childrenCount = ""
                        resultText = ""
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF0000),
                        contentColor = Color.White
                    )
                ) {
                    Text("Vymazat")
                }
            }

            // Výsledek
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (resultText.isNotEmpty()) {
                    Text(
                        text = resultText,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeExample()
}
