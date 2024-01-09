package com.example.mytasks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateCard : AppCompatActivity() {
    private lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "MyTask"
        ).build()
        val create_title = findViewById<EditText>(R.id.create_title)
        val create_priority = findViewById<EditText>(R.id.create_priority)
        val save_button = findViewById<Button>(R.id.save_button)
        val fTime = findViewById<EditText>(R.id.fromTime)
        val tTime = findViewById<EditText>(R.id.toTime)
        val fDate = findViewById<EditText>(R.id.fromDate)
        val tDate = findViewById<EditText>(R.id.toDate)

        save_button.setOnClickListener {
            if (create_title.text.toString().trim { it <= ' ' }.isNotEmpty() and
                create_priority.text.toString().trim { it <= ' ' }.isNotEmpty() and
                fTime.text.toString().trim { it <= ' ' }.isNotEmpty() and
                tTime.text.toString().trim { it <= ' ' }.isNotEmpty() and
                fDate.text.toString().trim { it <= ' ' }.isNotEmpty() and
                tDate.text.toString().trim { it <= ' ' }.isNotEmpty()) {
                var title = create_title.text.toString()
                var priority = create_priority.text.toString()
                var ftime = fTime.text.toString()
                var ttime = tTime.text.toString()
                var fdate = fDate.text.toString()
                var tdate = tDate.text.toString()

                DataObject.setData(title, priority, ftime, ttime, fdate, tdate, false)
                GlobalScope.launch {
                    database.dao().insertTask(Entity(0, title, priority, ftime, ttime, fdate, tdate, false))

                }

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}