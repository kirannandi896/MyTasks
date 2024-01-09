package com.example.mytasks

import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class MainActivity : AppCompatActivity() {
    private lateinit var database: myDatabase
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var sharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "MyTask"
        ).build()
        val add = findViewById<Button>(R.id.add)
        val switch = findViewById<Switch>(R.id.themechanger)
        val back = findViewById<LinearLayout>(R.id.mall_back)
        val search = findViewById<ImageButton>(R.id.searchbtn)
        val all = findViewById<TextView>(R.id.all)
        val high = findViewById<TextView>(R.id.highfil)
        val medium = findViewById<TextView>(R.id.medfil)
        val low = findViewById<TextView>(R.id.lowfil)
        val done = findViewById<TextView>(R.id.donefil)
        val login = findViewById<Button>(R.id.login)

        sharedPref = getSharedPreferences("MODE", MODE_PRIVATE)
        val nightMode = sharedPref.getBoolean("nightMode",false)

        if(nightMode){
            switch.setChecked(true)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        switch.setOnClickListener {
            if(nightMode){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor = sharedPref.edit()
                editor.putBoolean("nightMode",false)
            }
            else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor = sharedPref.edit()
                editor.putBoolean("nightMode",true)
            }
            editor.apply()
        }

        all.setOnClickListener{
            setRecycler()
            all.setBackgroundColor(Color.parseColor("#22FFFFFF"))
            high.setBackgroundColor(Color.parseColor("#6750A2"))
            medium.setBackgroundColor(Color.parseColor("#6750A2"))
            low.setBackgroundColor(Color.parseColor("#6750A2"))
            done.setBackgroundColor(Color.parseColor("#6750A2"))
        }

        high.setOnClickListener{
            val recycler_view = findViewById<RecyclerView>(R.id.recycler_view)
            recycler_view.adapter = Adapter(DataObject.filterlist("high"))
            recycler_view.layoutManager = LinearLayoutManager(this)
            all.setBackgroundColor(Color.parseColor("#6750A2"))
            high.setBackgroundColor(Color.parseColor("#22FFFFFF"))
            medium.setBackgroundColor(Color.parseColor("#6750A2"))
            low.setBackgroundColor(Color.parseColor("#6750A2"))
            done.setBackgroundColor(Color.parseColor("#6750A2"))
        }

        medium.setOnClickListener{
            val recycler_view = findViewById<RecyclerView>(R.id.recycler_view)
            recycler_view.adapter = Adapter(DataObject.filterlist("medium"))
            recycler_view.layoutManager = LinearLayoutManager(this)
            all.setBackgroundColor(Color.parseColor("#6750A2"))
            high.setBackgroundColor(Color.parseColor("#6750A2"))
            medium.setBackgroundColor(Color.parseColor("#22FFFFFF"))
            low.setBackgroundColor(Color.parseColor("#6750A2"))
            done.setBackgroundColor(Color.parseColor("#6750A2"))
        }

        low.setOnClickListener{
            val recycler_view = findViewById<RecyclerView>(R.id.recycler_view)
            recycler_view.adapter = Adapter(DataObject.filterlist("low"))
            recycler_view.layoutManager = LinearLayoutManager(this)
            all.setBackgroundColor(Color.parseColor("#6750A2"))
            high.setBackgroundColor(Color.parseColor("#6750A2"))
            medium.setBackgroundColor(Color.parseColor("#6750A2"))
            low.setBackgroundColor(Color.parseColor("#22FFFFFF"))
            done.setBackgroundColor(Color.parseColor("#6750A2"))
        }

        done.setOnClickListener{
            val recycler_view = findViewById<RecyclerView>(R.id.recycler_view)
            recycler_view.adapter = Adapter(DataObject.filterlistdone(true))
            recycler_view.layoutManager = LinearLayoutManager(this)
            all.setBackgroundColor(Color.parseColor("#6750A2"))
            high.setBackgroundColor(Color.parseColor("#6750A2"))
            medium.setBackgroundColor(Color.parseColor("#6750A2"))
            low.setBackgroundColor(Color.parseColor("#6750A2"))
            done.setBackgroundColor(Color.parseColor("#22FFFFFF"))
        }

        search.setOnClickListener{
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        login.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }



        add.setOnClickListener {
            val intent = Intent(this, CreateCard::class.java)
            startActivity(intent)
        }
//        deleteAll.setOnClickListener {
//            DataObject.deleteAll()
//            GlobalScope.launch {
//                database.dao().deleteAll()
//            }
//            setRecycler()
//        }

        setRecycler()

    }

    fun setRecycler() {
        val recycler_view = findViewById<RecyclerView>(R.id.recycler_view)
        recycler_view.adapter = Adapter(DataObject.getAllData())
        recycler_view.layoutManager = LinearLayoutManager(this)
    }

}