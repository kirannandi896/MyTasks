package com.example.mytasks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.room.Room
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val username = findViewById<TextInputEditText>(R.id.r_email)
        val password = findViewById<TextInputEditText>(R.id.r_password)
        val registerbtn = findViewById<Button>(R.id.rregister)
        val loginbtn = findViewById<Button>(R.id.rlogin)

        loginbtn.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        registerbtn.setOnClickListener{
            if (username.text.toString().trim { it <= ' ' }.isNotEmpty() and
                password.text.toString().trim { it <= ' ' }.isNotEmpty()) {
                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, SplashScreen::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Invalid Registration", Toast.LENGTH_SHORT).show()
            }
        }

    }
}