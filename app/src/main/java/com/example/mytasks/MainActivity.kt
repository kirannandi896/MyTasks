package com.example.mytasks

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.Switch
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
//import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


// entity - table
// dao - queries
// database

class MainActivity : AppCompatActivity() {
    private lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "Tasks"
        ).build()
        val add = findViewById<Button>(R.id.add)
        val deleteAll = findViewById<Button>(R.id.deleteAll)
        val switch = findViewById<Switch>(R.id.themechanger)
        val back = findViewById<LinearLayout>(R.id.mall_back)

        switch.setOnClickListener{
            if(switch.isChecked){
                back.setBackgroundColor(Color.parseColor("#FF000000"))
            }
            else{
                back.setBackgroundColor(Color.parseColor("#C9CDF3"))
            }
        }

        add.setOnClickListener {
            val intent = Intent(this, CreateCard::class.java)
            startActivity(intent)
        }
        deleteAll.setOnClickListener {
            DataObject.deleteAll()
            GlobalScope.launch {
                database.dao().deleteAll()
            }
            setRecycler()
        }

        setRecycler()

    }

    fun setRecycler() {
        val recycler_view = findViewById<RecyclerView>(R.id.recycler_view)
        recycler_view.adapter = Adapter(DataObject.getAllData())
        recycler_view.layoutManager = LinearLayoutManager(this)
    }




}