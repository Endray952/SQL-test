package com.example.sql_test.data_base

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, DataBaseConsts.DATABASE_NAME, null, DataBaseConsts.DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(DataBaseConsts.SQL_CREATE_FILMS_TABLE_1000)
        db?.execSQL(DataBaseConsts.SQL_CREATE_FILMS_TABLE_10_000)
        db?.execSQL(DataBaseConsts.SQL_CREATE_FILMS_TABLE_100_000)
        db?.execSQL(DataBaseConsts.SQL_CREATE_FILMS_TABLE_1000_COPY)
        db?.execSQL(DataBaseConsts.SQL_CREATE_FILMS_TABLE_10_000_COPY)
        db?.execSQL(DataBaseConsts.SQL_CREATE_FILMS_TABLE_100_000_COPY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DataBaseConsts.SQL_DELETE_FILMS_TABLE_1000)
        db?.execSQL(DataBaseConsts.SQL_DELETE_FILMS_TABLE_10_000)
        db?.execSQL(DataBaseConsts.SQL_DELETE_FILMS_TABLE_100_000)
        db?.execSQL(DataBaseConsts.SQL_DELETE_FILMS_TABLE)

        db?.execSQL(DataBaseConsts.SQL_DELETE_FILMS_TABLE_1000_COPY)
        db?.execSQL(DataBaseConsts.SQL_DELETE_FILMS_TABLE_10_000_COPY)
        db?.execSQL(DataBaseConsts.SQL_DELETE_FILMS_TABLE_100_000_COPY)
        onCreate(db);
    }
}