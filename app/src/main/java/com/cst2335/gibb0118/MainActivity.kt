package com.cst2335.gibb0118

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.accessibility.AccessibilityEventCompat.setAction
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_grid)

        val button = findViewById<Button>(R.id.button2)
        val switch = findViewById<Switch>(R.id.switch2)

        button.setOnClickListener {
            Toast.makeText(
                applicationContext,
                resources.getString(R.string.moreInfoToast),
                Toast.LENGTH_LONG
            ).show()
        }

        switch.setOnCheckedChangeListener { compoundButton, b ->
            if (b){
                Snackbar.make(compoundButton,resources.getString(R.string.SnackbarOn),Snackbar.LENGTH_LONG).setAction("Undo") { _ ->
                    switch.isChecked = false
                }.show()
            }

        }


    }


}


