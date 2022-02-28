package com.cst2335.gibb0118

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView

class ChatListAdapter : ArrayAdapter<ChatMessage> {





    override fun getCount(): Int {
        return super.getCount()
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return super.getView(position, convertView, parent)
    }

    override fun getItem(position: Int): ChatMessage? {
        return super.getItem(position)
    }
}