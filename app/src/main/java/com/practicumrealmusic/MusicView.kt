package com.practicumrealmusic

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MusicView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_view)

        val SongListView: ListView = findViewById(R.id.listView)
        val backButton: Button = findViewById(R.id.backButton)

        val items = intent.getStringArrayListExtra("items") ?: arrayListOf()
        val categories = intent.getStringArrayListExtra("categories") ?: arrayListOf()
        val quantities = intent.getIntegerArrayListExtra("quantities") ?: arrayListOf()
        val comments = intent.getStringArrayListExtra("comments") ?: arrayListOf()

        val filteredList = mutableListOf<String>()
        for (i in items.indices) {
            if (quantities[i] >= 2) filteredList.add("${items[i]} (${categories[i]}, Qty: ${quantities[i]})\\nNote: ${comments[i]}\")
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, filteredList)
        SongListView.adapter = adapter

        backButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}