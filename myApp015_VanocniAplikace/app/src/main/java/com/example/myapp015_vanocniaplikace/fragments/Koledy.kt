package com.example.myapp015_vanocniaplikace.fragments
import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapp015_vanocniaplikace.R
import com.example.myapp015_vanocniaplikace.databinding.FragmentKoledyBinding
import java.io.IOException
import java.lang.reflect.Field
class Koledy : Fragment(R.layout.fragment_koledy) {

    private var _binding: FragmentKoledyBinding? = null
    private val binding get() = _binding!!

    private var mediaPlayer: MediaPlayer? = null
    private var currentSongIndex = 0
    private var currentPosition = 0 // Store the current position of the song

    // Seznam skladeb
    private val songs = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKoledyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Načteme soubory z raw složky
        loadSongsFromRaw()

        setupButtons()
        updateNowPlayingText()
    }

    private fun setupButtons() {
        binding.KPlay.setOnClickListener {
            if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
                // Pause if playing
                mediaPlayer?.pause()
                currentPosition = mediaPlayer?.currentPosition ?: 0
                binding.KPlay.visibility = View.VISIBLE // Change icon to play
                binding.KStop.visibility = View.GONE // Show stop button
            } else {
                // Play or resume from paused position
                playCurrentSong()
            }
        }

        binding.KNext.setOnClickListener {
            playNextSong()
        }

        binding.KPrev.setOnClickListener {
            playPreviousSong()
        }

        // Stop button
        binding.KStop.setOnClickListener {
            stopCurrentSong()
        }
    }
    private fun formatSongName(songName: String): String {
        return songName
            .split("_")  // Rozdělí text podle podtržítek
            .joinToString(" ") { it.replaceFirstChar { char -> char.uppercase() } }  // Každé slovo začne velkým písmenem
    }
    private fun loadSongsFromRaw() {
        try {
            val files = com.example.myapp015_vanocniaplikace.R.raw::class.java.declaredFields

            files.forEach { field ->
                val songName = field.name
                if (!songName.contains("___")) {
                    Log.d("Knames", String.format(
                        "name=\"%s\", id=0x%08x",
                        songName, field.getInt(field)
                    ))
                    // Add to songs list if the name doesn't contain "___"
                    songs.add(songName) // Add the name of the song (without file extension)
                } // Add the song name without extension
            }

            if (songs.isNotEmpty()) {
                currentSongIndex = 0
                binding.NowPlaying.text = formatSongName(songs[currentSongIndex])
                playCurrentSong()  // Play the first song
            } else {
                Log.e("KoledyFragment", "Žádné písničky ve složce raw!")
            }
        } catch (e: Exception) {
            Log.e("KoledyFragment", "Chyba při načítání souborů: ${e.message}")
        }
    }
    private fun playCurrentSong() {
        if (songs.isNotEmpty()) {
            try {
                val songName = songs[currentSongIndex]
                val songResId = resources.getIdentifier(songName, "raw", requireContext().packageName)

                if (songResId != 0) {
                    // Pokud již existuje mediaPlayer, zastavíme ho a uvolníme
                    mediaPlayer?.let {
                        it.stop()
                        it.release()
                    }

                    // Vytvoření nového MediaPlayer
                    mediaPlayer = MediaPlayer.create(requireContext(), songResId)

                    // Pokud byla písnička pozastavena, pokračujeme od její pozice
                    if (currentPosition > 0) {
                        mediaPlayer?.seekTo(currentPosition) // Continue from the paused position
                    }

                    mediaPlayer?.start()  // Start the song

                    Log.d("KoledyFragment", "Přehrává se: $songName")

                    // Změníme viditelnost tlačítek
                    binding.NowPlaying.text=formatSongName(songName)
                    binding.KPlay.visibility = View.GONE
                    binding.KStop.visibility = View.VISIBLE
                } else {
                    Log.e("KoledyFragment", "Soubor $songName nenalezen v raw složce.")
                }
            } catch (e: Exception) {
                Log.e("KoledyFragment", "Chyba při přehrávání písničky: ${e.message}")
            }
        }
    }
    private fun stopCurrentSong() {
        mediaPlayer?.let {
            // Save the current position before stopping
            currentPosition = it.currentPosition
            it.pause()  // Pause the song instead of stopping

            Log.d("KoledyFragment", "Přehrávání pozastaveno na pozici: $currentPosition")

            // Change button visibility
            binding.KPlay.visibility = View.VISIBLE // Change icon to play
            binding.KStop.visibility = View.GONE
        }
    }
    private fun playNextSong() {
        // Posun index na další písničku, nebo na začátek seznamu
        currentSongIndex = (currentSongIndex + 1) % songs.size
        binding.NowPlaying.text = songs[currentSongIndex]
        playCurrentSong()  // Play the next song
    }
    private fun playPreviousSong() {
        // Posun index na předchozí písničku, nebo na konec seznamu
        currentSongIndex = if (currentSongIndex - 1 < 0) songs.size - 1 else currentSongIndex - 1
        binding.NowPlaying.text = songs[currentSongIndex]
        playCurrentSong()  // Play the previous song
    }
    private fun updateNowPlayingText() {
        if (songs.isNotEmpty()) {
            binding.NowPlaying.text = formatSongName(songs[currentSongIndex])  // Update the song title
        } else {
            binding.NowPlaying.text = "Žádné písničky k přehrání"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        // Uvolníme MediaPlayer
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
