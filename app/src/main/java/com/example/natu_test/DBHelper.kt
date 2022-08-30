package com.example.natu_test

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper:SQLiteOpenHelper {
    constructor(context: Context) : super(context, "DB", null, 1)

    val TB_TODO : String = "UangMasuk"
    val COL_ID : String = "UangMasukID"
    val COL_TERIMA : String = "TerimaDari"
    val COL_KETERANGAN : String = "Keterangan"
    val COL_JUMLAH : String = "jumlah"

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var sql: String = "CREATE TABLE $TB_TODO ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_TERIMA VARCHAR(30), $COL_KETERANGAN TEXT, $COL_JUMLAH INT)"
        db?.execSQL(sql)
    }

    fun insertData(UangMasuk: UangMasuk): Boolean{
        val db: SQLiteDatabase = this.writableDatabase

        var cv = ContentValues()
        cv.put(COL_TERIMA, UangMasuk.terima_dari)
        cv.put(COL_KETERANGAN, UangMasuk.keterangan)
        cv.put(COL_JUMLAH, UangMasuk.jumlah)
        return db.insert(TB_TODO, null, cv) > 0

    }
}