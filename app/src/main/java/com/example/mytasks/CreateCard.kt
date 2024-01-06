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
//    lateinit var create_priority: RadioGroup
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "Tasks"
        ).build()
        val create_title = findViewById<EditText>(R.id.create_title)
        val create_priority = findViewById<EditText>(R.id.create_priority)
        val save_button = findViewById<Button>(R.id.save_button)
//        create_priority.setOnCheckedChangeListener { group, checkedId ->
//            val radioButton = findViewById<RadioButton>(checkedId)
//            Toast.makeText(
//                this@CreateCard,
//                "Selected Radio Button is : " + radioButton.text,
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//        val selectedId = create_priority.checkedRadioButtonId
//        val radioButton = findViewById<RadioButton>(selectedId)
        save_button.setOnClickListener {
            if (create_title.text.toString().trim { it <= ' ' }.isNotEmpty()) {
                var title = create_title.text.toString()
                var priority = create_priority.text.toString()
                DataObject.setData(title, priority, false)
                GlobalScope.launch {
                    database.dao().insertTask(Entity(0, title, priority, false))

                }

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}