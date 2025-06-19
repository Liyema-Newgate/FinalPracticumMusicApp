package com.practicumrealmusic
//
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    // Parallel arrays storing initial data of artists,song names etc in 4 parallel arrays
    private val songNames = mutableListOf("Backdoor", "Luther", "Pillars", "Free the Frail")
    private val artists = mutableListOf("Playboicarti", "Kendrick ", "Navy Blue", "Jpegmafia")
    private val ratings = mutableListOf(4, 1, 2, 3)
    private val comments = mutableListOf(
        "Good Rap Song ",
        "Great R&B Song",
        "Sweet Mourning Song",
        "Calm Vibe Song"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//where user inputs the data into fields
        val songInput: EditText = findViewById(R.id.songInput)
        val artistInput: EditText = findViewById(R.id.artistInput)
        val ratingInput: EditText = findViewById(R.id.ratingInput)
        val commentInput: EditText = findViewById(R.id.commentInput)

        val addButton: Button = findViewById(R.id.addButton)
        val viewMusicButton: Button = findViewById(R.id.viewMusicButton)
        val exitButton: Button = findViewById(R.id.exitButton)

        addButton.setOnClickListener {
            val song = songInput.text.toString()
            val artist = artistInput.text.toString()
            val ratingText = ratingInput.text.toString()
            val comment = commentInput.text.toString()
//following is to error handle and display the error messages
            if (song.isBlank() || artist.isBlank() || ratingText.isBlank() || comment.isBlank()) {
                Toast.makeText(this, "All fields are required.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val quantity = ratingText.toIntOrNull()
            if (quantity == null || quantity <= 0) {
                Toast.makeText(this, "Quantity must be a positive number.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            songNames.add(song)
            artists.add(artist)
            ratings.add(quantity)
            comments.add(comment)

            Toast.makeText(this, "Song added successfully.", Toast.LENGTH_SHORT).show()
            songInput.text.clear()
            artistInput.text.clear()
            ratingInput.text.clear()
            commentInput.text.clear()
        }
//button to click that launches the 2nd class
        viewMusicButton.setOnClickListener {
            val intent = Intent(this, MusicView::class.java)
            intent.putStringArrayListExtra("Songs", ArrayList(songNames))
            intent.putStringArrayListExtra("Artists", ArrayList(artists))
            intent.putIntegerArrayListExtra("Ratings", ArrayList(ratings))
            intent.putStringArrayListExtra("comments", ArrayList(comments))
            startActivity(intent)
        }

        exitButton.setOnClickListener {
            finish()
        }
    }


    }
