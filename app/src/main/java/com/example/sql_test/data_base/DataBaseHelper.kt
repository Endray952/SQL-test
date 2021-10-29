package com.example.sql_test.data_base

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, DataBaseConsts.DATABASE_NAME, null, DataBaseConsts.DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(DataBaseConsts.SQL_CREATE_FILMS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DataBaseConsts.SQL_DELETE_FILMS_TABLE)
        onCreate(db);
    }
}