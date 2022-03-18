package com.cst2335.gibb0118

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class Database(val context: Context?, dbName: String, dbVersion: Int) :
    SQLiteOpenHelper(context, dbName, null, dbVersion) {



    constructor(context: Context?) : this(context, DATABASE_NAME, VERSION)


    companion object {
        const val ACTIVITY_NAME = "Database"
        const val DATABASE_NAME = "ChatDatabase"
        const val TABLE_NAME = "ChatTable"
        const val KEY_ID = "id"
        const val KEY_MESSAGE = "message"
        const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {


        db?.execSQL(
            " CREATE TABLE " + TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY, " +
                    KEY_MESSAGE + " TEXT)"
        )

        Log.i(ACTIVITY_NAME, "Calling onCreate")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME;")
        onCreate(db)
        Log.i(ACTIVITY_NAME, "Calling onUpdate(), oldVersion=$oldVersion, newVersion=$newVersion")
    }


}