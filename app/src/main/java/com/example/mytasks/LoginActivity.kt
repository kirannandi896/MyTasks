package com.example.mytasks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<TextInputEditText>(R.id.l_email)
        val password = findViewById<TextInputEditText>(R.id.l_password)
        val registerbtn = findViewById<Button>(R.id.registerbtn)
        val loginbtn = findViewById<Button>(R.id.loginbtn)

        registerbtn.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        loginbtn.setOnClickListener{
            if(username.text.toString() == "abc@gmail.com" && password.text.toString() == "password") {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, SplashScreen::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Invalid Login", Toast.LENGTH_SHORT).show()
            }

        }
    }
}