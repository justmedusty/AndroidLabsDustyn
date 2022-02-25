package com.cst2335.gibb0118

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.*

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val imageButton = findViewById<ImageButton>(R.id.imageButton4)
        val chatButton = findViewById<Button>(R.id.button6)
        updateEmail()

        imageButton.setOnClickListener {
            dispatchTakePictureIntent()
        }

        chatButton.setOnClickListener {
            nextActivity()
        }


    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "Paused")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(TAG, "Restart")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "Resumed")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "Destroyed")
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (takePictureIntent.resolveActivity(packageManager) != null) {

            myPictureTakerLauncher.launch(takePictureIntent)
        }


    }

    private val myPictureTakerLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        val imageButton = findViewById<ImageButton>(R.id.imageButton4)
        when (result.resultCode) {
            Activity.RESULT_OK -> {
                val data: Intent = result.data!!
                val imgBitMap: Bitmap = data.extras?.get("data") as Bitmap
                imageButton.setImageBitmap(imgBitMap)
            }
            Activity.RESULT_CANCELED -> {
                Log.i(TAG, "User refused to capture image")
            }
        }
    }


    private fun updateEmail() {
        val sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        val email = findViewById<EditText>(R.id.editTextTextPersonName5)
        val fromMain: Intent = intent
        fromMain.getStringExtra("EMAIL")
        email.setText(sharedPreferences.getString("EMAIL", "EMAIL"))
    }
    private fun nextActivity() {
        val nextPage = Intent(this, ChatActivity::class.java)
        //nextPage.putExtra("EMAIL","EMAIL")

        startActivity(nextPage)

    }


}
