package com.isma.criapokemon.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Sqlite(context: Context): SQLiteOpenHelper(context, "criapokemon", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        TODO()

        //db.execSQL("CREATE TABLE caja ()")

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}