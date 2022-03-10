package com.cst2335.gibb0118

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class ChatListAdapter(
    private val context: Context, val  textViewResourceId: Int

) : BaseAdapter() {
    val dataSource: ArrayList<ChatMessage> = ArrayList()



    override fun getCount(): Int {
        return this.dataSource.size
    }

    override fun getItem(index: Int): Any {
        return dataSource[index]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

        val chatMessageObj: ChatMessage = getItem(position) as ChatMessage
        var row: View? = convertView
        val inflater: LayoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        row = if (chatMessageObj.isSent) {
            inflater.inflate(R.layout.right, parent, false)
        } else {
            inflater.inflate(R.layout.left, parent, false)
        }

        val chatText : TextView = row.findViewById(R.id.text)
        chatText.text = chatMessageObj.text

        return row


    }
}

