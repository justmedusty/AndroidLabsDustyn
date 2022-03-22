package com.cst2335.gibb0118

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ChatActivity : AppCompatActivity() {

    private val TAG: String = "ChatActivity"
    private lateinit var sendButton: Button
    private lateinit var receiveButton: Button
    private lateinit var editText: EditText
    private lateinit var chatListAdapter: ChatListAdapter
    private lateinit var listView: ListView

    private val contentValues: ContentValues = ContentValues()
    private var chatDB: SQLiteDatabase = SQLiteDatabase.create(null)



    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chatroom_acitivty)


        val context: Context = applicationContext





        sendButton = findViewById(R.id.send)
        receiveButton = findViewById(R.id.receive)
        editText = findViewById(R.id.msg)
        chatListAdapter = ChatListAdapter(applicationContext, R.layout.right)
        listView = findViewById(R.id.msgview)
        listView.adapter = chatListAdapter


        val chatDbHelper = Database(context)
        chatDB = chatDbHelper.writableDatabase

       val cursor : Cursor = chatDB.query(
            Database.TABLE_NAME,
            arrayOf(Database.KEY_MESSAGE,Database.IS_SENT),
            null,
            null,
            null,
            null,
            null,
            null
        )


        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                if (cursor.getColumnIndex(Database.KEY_MESSAGE) < 0){
                    cursor.moveToFirst()
                }
                val message: String =  cursor.getString(cursor.getColumnIndexOrThrow(Database.KEY_MESSAGE))
                val sent: Boolean = cursor.getInt(cursor.getColumnIndex(Database.IS_SENT)) > 0
                chatListAdapter.dataSource.add(ChatMessage(message, sent))
                cursor.moveToNext()
            }
            cursor.close()
        }



        Log.i(TAG, "Cursor's column count=" + cursor.columnCount)
        for (i in 0 until cursor.columnCount) {
            Log.i(TAG, cursor.getColumnName(i))
        }



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
        if (editText.text.isNullOrBlank()) {
            return
        }

        chatListAdapter.apply {
            var isSentInt = 0
            if (isSent) {
                isSentInt = 1
            }

            val chatMessage = ChatMessage(editText.text.toString(), isSent)
            editText.setText("")
            dataSource.add(chatMessage)
            contentValues.put(Database.KEY_MESSAGE, chatMessage.text)
            contentValues.put(Database.IS_SENT, isSentInt)
            chatDB.insert(Database.TABLE_NAME, null, contentValues)
            chatListAdapter.notifyDataSetChanged()

        }

    }


}