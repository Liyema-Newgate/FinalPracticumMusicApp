package com.practicumrealmusic
//this class is for screen two and viewing the songs and
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MusicView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_view)

        val songListView: ListView = findViewById(R.id.listView)
        val backButton: Button = findViewById(R.id.backButton)

        val songNames = intent.getStringArrayListExtra("SongsNames") ?: arrayListOf()
        val artists = intent.getStringArrayListExtra("Artists") ?: arrayListOf()
        val ratings = intent.getIntegerArrayListExtra("Ratings") ?: arrayListOf()
        val comments = intent.getStringArrayListExtra("comments") ?: arrayListOf()
//will display all songs equal to or above a 1 rating. easy way to list everything
        val filteredList = mutableListOf<String>()
        for (i in ratings.indices) {
            if (ratings[i] >= 1)
                filteredList.add("${songNames[i]} (${artists[i]}, Qty: ${ratings[i]})\\nNote: ${comments[i]}\"")
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, filteredList)
        songListView.adapter = adapter
//back button to return to screen 1
        backButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
