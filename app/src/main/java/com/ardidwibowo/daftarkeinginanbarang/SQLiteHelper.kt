package com.ardidwibowo.daftarkeinginanbarang

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper (context: Context):
    SQLiteOpenHelper(context, DATABASE_NAME,null,DATABASE_VERSION){

        companion object{

            //TODO : Mendefisikan nilai konstranta yang digunakan
            private  const val DATABASE_VERSION = 1
            private  const val DATABASE_NAME = "barang.db"
            private  const val TBL_BARANG = "tbl_barang"
            private const val  ID = "id"
            private const val NAMA = "nama"
            private  const val TYPE ="type"
            private const val TANGGAL = "tanggal"
        }

    override fun onCreate(db : SQLiteDatabase?){
        //TODO : membuat perintah table sesuai dengan nilai kostanta yang digunakan
        val createTbBarang = ("CREATE TABLE " + TBL_BARANG +"("
                                + ID+ " INTEGER PRIMARY KEY,"+ NAMA+" TEXT,"
                                + TYPE+" TEXT,"+ TANGGAL+" TEXT "+")")
        //TODO : Jalanak perintah diatas
        db?.execSQL(createTbBarang)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_BARANG")
        onCreate(db)
    }

    //TODO : Membuat fungsi insertBarang
    fun insertBarang (brg : BarangModel): Long {
        //TODO : Membaca database yang ditulis saat ini -> db
        val db = this.writableDatabase
        //TODO : Membuat mengakse package ContentValue -> contentValues
        val contentValues = ContentValues()

        //TODO : Menerima meletakan data sesuai dengan kolom
        contentValues.put(ID, brg.id)
        contentValues.put(NAMA,brg.nama)
        contentValues.put(TYPE,brg.type)
        contentValues.put(TANGGAL,brg.tanggal)

        //TODO : jalankan perintah masukan data ke tabel yang sudah diletakan tadi -> succes
        val succes = db.insert(TBL_BARANG,null,contentValues)
        //TODO : Tutup database
        db.close()
        //TODO : Kembalikan variabel succes
        return succes

    }


    @SuppressLint("Range")

    //TODO : Membuat fungsi getAllBarang
    fun getAllBarang(): ArrayList<BarangModel>{
        //TODO : Membuat array dari class BarangModel -> brglist
        val brglist: ArrayList<BarangModel> = ArrayList()
        //TODO : Membuat perintah query untu menyeleksi semua data dari tabel -> selectQuery
        val selectQuery = "SELECT * FROM $TBL_BARANG"
        //TODO : baca databse yang digunakan saat ini -> db
        val db = this.readableDatabase

        //TODO : Menjalankan perintah selectquery -> cursor
        val cursor = db.rawQuery(selectQuery,null)
        //TODO : jika berhasil memindahkan cursor ke baris pertama
        if(cursor.moveToFirst()){
            //TODO : Lakukan perulangan untuk mengakses nilai setiap kolom dan mnegisi nilai class BarangModel
                // TODO : Mengisikan nilai ke berglist dari perulangan tadi
            do{
                val id = cursor.getString(cursor.getColumnIndex("id")).toInt()
                val nama = cursor.getString(cursor.getColumnIndex("nama"))
                val type = cursor.getString(cursor.getColumnIndex("type"))
                val tanggal = cursor.getString(cursor.getColumnIndex("tanggal"))
                val brg = BarangModel(id = id,nama = nama,type=type,tanggal=tanggal)
                brglist.add(brg)
            }while (cursor.moveToNext())
        }
        //TODO : kembalikan nilai brgList
        return brglist
    }

    //TODO : membuat fungsi deleteBarangById dengan parameter id
    fun deleteBarangById(id: Int):Int{
        //TODO : baca databse yang digunakan saat ini -> db
        val db = this.readableDatabase
        //TODO : Membuat mengakse package ContentValue -> contentValues
        val contentValues = ContentValues()
        //TODO : Menerima meletakan data sesuai dengan kolom
        contentValues.put(ID,id)
        //TODO : menghapus data berdasarkan id yang sudah ditentukan -> success
        val success = db.delete(TBL_BARANG, "id=$id", null)
        //TODO : mengebalikan nilai success
        return success
    }

}