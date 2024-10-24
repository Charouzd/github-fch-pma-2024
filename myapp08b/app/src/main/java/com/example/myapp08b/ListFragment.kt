package com.example.myapp08b

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView

class ListFragment : Fragment() {

    private lateinit var listView: ListView

    // Seznam ras s názvem, ID obrázku a popisem
    private val race = listOf(
        "Elf" to R.drawable.elf to "Elfové jsou mystická rasa...",
        "Human" to R.drawable.human to "Lidé jsou známí svou odolností...",
        "Troll" to R.drawable.trol to "Trolové jsou divocí a obávaní tvorové..."
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        listView = view.findViewById(R.id.listViewBooks)

        // Vytvoření ArrayAdapteru pro zobrazení názvů ras v listu
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            race.map { it.first.first }  // Vytáhne názvy ras pro zobrazení v seznamu
        )
        listView.adapter = adapter

        // Nastavení kliknutí na položku v seznamu
        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedRace = race[position]

            // selectedRace je Pair<Pair<String, Int>, String>
            val raceName = selectedRace.first.first      // "Elf", "Human", "Troll"
            val raceImage = selectedRace.first.second    // R.drawable.elf_image, R.drawable.human_image, R.drawable.troll_image
            val raceDescription = selectedRace.second    // Popis rasy

            // Předáme data do MainActivity pomocí metody onRaceSelected
            (activity as? MainActivity)?.onRaceSelected(
                raceImage,         // Obrázek
                raceDescription    // Popis
            )
        }

        return view
    }
}
