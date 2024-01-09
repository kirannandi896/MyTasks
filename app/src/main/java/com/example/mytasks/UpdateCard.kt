package com.example.mytasks

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class UpdateCard : AppCompatActivity() {
    private lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_card)
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "MyTask"
        ).build()
        val pos = intent.getIntExtra("id", -1)
        if (pos != -1) {
            var title = DataObject.getData(pos).title
            var priority = DataObject.getData(pos).priority
            var ftime = DataObject.getData(pos).ftime
            var ttime = DataObject.getData(pos).ttime
            var fdate = DataObject.getData(pos).fdate
            var tdate = DataObject.getData(pos).tdate
            var done = DataObject.getData(pos).done
            var create_title = findViewById<EditText>(R.id.create_title)
            var create_priority = findViewById<EditText>(R.id.create_priority)
            var create_fromDate = findViewById<EditText>(R.id.create_fromDate)
            var create_toDate = findViewById<EditText>(R.id.create_toDate)
            var create_fromTime = findViewById<EditText>(R.id.create_fromTime)
            var create_toTime = findViewById<EditText>(R.id.create_toTime)
            var markdone = findViewById<CheckBox>(R.id.markdone)
            var update_button = findViewById<Button>(R.id.update_button)
            var delete_button = findViewById<TextView>(R.id.delete_button)
            create_title.setText(title)
            create_priority.setText(priority)
            create_fromTime.setText(ftime)
            create_toTime.setText(ttime)
            create_fromDate.setText(fdate)
            create_toDate.setText(tdate)
            markdone.isChecked = done


            delete_button.setOnClickListener {
                DataObject.deleteData(pos)
                GlobalScope.launch {
                    database.dao().deleteTask(
                        Entity(
                            pos + 1,
                            create_title.text.toString(),
                            create_priority.text.toString(),
                            create_fromTime.text.toString(),
                            create_toTime.text.toString(),
                            create_fromDate.text.toString(),
                            create_toDate.text.toString(),
                            markdone.isChecked
                        )
                    )
                }
                myIntent()
            }

            update_button.setOnClickListener {
                DataObject.updateData(
                    pos,
                    create_title.text.toString(),
                    create_priority.text.toString(),
                    create_fromTime.text.toString(),
                    create_toTime.text.toString(),
                    create_fromDate.text.toString(),
                    create_toDate.text.toString(),
                    markdone.isChecked
                )
                GlobalScope.launch {
                    database.dao().updateTask(
                        Entity(
                            pos + 1,
                            create_title.text.toString(),
                            create_priority.text.toString(),
                            create_fromTime.text.toString(),
                            create_toTime.text.toString(),
                            create_fromDate.text.toString(),
                            create_toDate.text.toString(),
                            markdone.isChecked
                        )
                    )
                }
                myIntent()
            }

        }
    }

    fun myIntent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
