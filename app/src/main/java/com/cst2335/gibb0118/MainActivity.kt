package com.cst2335.gibb0118

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lab3)

        loadCredentials()
        checkEmptyFields()
    }


    override fun onPause() {
        super.onPause()
        saveCredentials()
    }


    private fun loadCredentials() {
        val email = findViewById<EditText>(R.id.editTextEmailAddress)
        val password = findViewById<EditText>(R.id.editTextPassword)

        val sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        val savedEmail: String = sharedPreferences.getString("EMAIL", "").toString()
        val savedPassword: String = sharedPreferences.getString("PASSWORD", "").toString()

        email.setText(savedEmail)
        password.setText(savedPassword)

    }


    private fun saveCredentials() {

        val email = findViewById<EditText>(R.id.editTextEmailAddress)
        val password = findViewById<EditText>(R.id.editTextPassword)
        val insertedEmail = email.text.toString()
        val insertedPassword = password.text.toString()

        val sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        val edit: SharedPreferences.Editor = sharedPreferences.edit()

        edit.apply {
            putString("EMAIL", insertedEmail)
            putString("PASSWORD", insertedPassword)
        }.apply()
        Toast.makeText(this, "Credentials saved", Toast.LENGTH_LONG).show()
    }


    private fun checkEmptyFields() {

        val button = findViewById<Button>(R.id.button5)
        val email = findViewById<EditText>(R.id.editTextEmailAddress)
        val password = findViewById<EditText>(R.id.editTextPassword)

        button.setOnClickListener {
            if (email.text.isNullOrBlank() || password.text.isNullOrBlank()) {
                Toast.makeText(this, "Please fill in the fields", Toast.LENGTH_SHORT).show()
            } else nextActivity()
        }
    }

    private fun nextActivity() {

        val nextPage = Intent(this, ProfileActivity::class.java)
        nextPage.putExtra("EMAIL","EMAIL")

        startActivity(nextPage)

    }


}




