package com.example.myapp08b

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class DetailFragment : Fragment() {

    private lateinit var imageViewDetail: ImageView
    private lateinit var textViewDetail: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        imageViewDetail = view.findViewById(R.id.imageViewDetail)
        textViewDetail = view.findViewById(R.id.textViewDetail)

        // Načtení argumentů a aktualizace zobrazení
        arguments?.let {
            val imageResId = it.getInt("imageResId")
            val detailText = it.getString("detailText")
            updateDetails(imageResId, detailText ?: "No details available")
        }

        return view
    }

    // Metoda pro aktualizaci detailů
    fun updateDetails(imageResId: Int, detailText: String) {
        imageViewDetail.setImageResource(imageResId)
        textViewDetail.text = detailText
    }
}
