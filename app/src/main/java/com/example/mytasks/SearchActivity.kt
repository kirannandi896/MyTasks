package com.example.mytasks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

private lateinit var database: myDatabase
class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "MyTask"
        ).build()

        val search = findViewById<EditText>(R.id.search)
        val clickSearch = findViewById<Button>(R.id.searchgo)
        val results = findViewById<RecyclerView>(R.id.results)

        clickSearch.setOnClickListener{
            if(search.text.toString().trim { it<= ' ' }.isNotEmpty()){
                results.adapter = Adapter(DataObject.searchlist(search.text.toString().toLowerCase()))
                results.layoutManager = LinearLayoutManager(this)
            }
        }
    }
}