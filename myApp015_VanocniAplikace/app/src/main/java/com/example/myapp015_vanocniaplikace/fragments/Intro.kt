package com.example.myapp015_vanocniaplikace.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapp015_vanocniaplikace.R
import com.example.myapp015_vanocniaplikace.databinding.FragmentIntroBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class Intro : Fragment(R.layout.fragment_intro) {
    private var _binding: FragmentIntroBinding? = null
    private val binding get() = _binding!!

    private val text1 = "Dnes je "
    private val text2 = "tím pádem do Vánoc chybí už jen "

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIntroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTextToTextView()
    }

    private fun setTextToTextView() {
        // Získání aktuálního data
        val currentDate = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val todayDate = dateFormat.format(currentDate.time)

        // Nastavení data Vánoc (24. prosince)
        val christmasDate = Calendar.getInstance()
        christmasDate.set(Calendar.MONTH, Calendar.DECEMBER)
        christmasDate.set(Calendar.DAY_OF_MONTH, 24)

        // Pokud jsou Vánoce již letos prošly, nastavíme datum na příští rok
        if (currentDate.after(christmasDate)) {
            christmasDate.add(Calendar.YEAR, 1)
        }

        // Výpočet počtu dní do Vánoc
        val diffInMillis = christmasDate.timeInMillis - currentDate.timeInMillis
        val daysUntilChristmas = (diffInMillis / (1000 * 60 * 60 * 24)).toInt()

        // Sestavení textu
        val finalText = "$text1 $todayDate $text2 $daysUntilChristmas dní"

        // Nastavení textu do TextView
        binding.textView2.text = finalText
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
