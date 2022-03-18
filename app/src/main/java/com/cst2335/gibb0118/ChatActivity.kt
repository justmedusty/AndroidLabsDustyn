package com.cst2335.gibb0118

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

abstract class ChatActivity : AppCompatActivity() {

    private val TAG: String = "ChatActivity"
    private lateinit var sendButton: Button
    private lateinit var receiveButton: Button
    private lateinit var editText: EditText
    private lateinit var chatListAdapter: ChatListAdapter
    private lateinit var listView: ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chatroom_acitivty)

        val chatDB : SQLiteDatabase
        val context : Context = applicationContext


        sendButton = findViewById(R.id.send)
        receiveButton = findViewById(R.id.receive)
        editText = findViewById(R.id.msg)
        chatListAdapter = ChatListAdapter(applicationContext, R.layout.right)
        listView = findViewById(R.id.msgview)
        listView.adapter = chatListAdapter


        val chatDbHelper : Database = Database(context)
        chatDB =  chatDbHelper.writableDatabase
        val cValues : ContentValues = ContentValues()

        val cursor : Cursor = chatDB.query(Database.TABLE_NAME, )



        sendButton.setOnClickListener {

            sendChatMessage(true)
            println(editText)
        }
        receiveButton.setOnClickListener {
            sendChatMessage(false)
        }

        listView.setOnItemClickListener { _, _, position, id ->
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Do you want to delete this message?")
                .setMessage(
                    "The selected row is :" + position + " and the database id is "
                            + id
                )
                .setPositiveButton("Yes") { _, _ ->
                    chatListAdapter.dataSource.removeAt(position)
                    chatListAdapter.notifyDataSetChanged()
                }
                .setNegativeButton("No") { _, _ ->

                }
                .create().show()

        }


    }

    private fun sendChatMessage(isSent: Boolean) {
      if (editText.text.isNullOrBlank()){
          return
      }

        chatListAdapter.apply {


            val chatMessage = ChatMessage(editText.text.toString(), isSent)
            editText.setText("")
            dataSource.add(chatMessage)
            chatListAdapter.notifyDataSetChanged()


        }

    }

}