package com.ardidwibowo.daftarkeinginanbarang

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AddActivity : AppCompatActivity() {
    //TODO : Mendifinisikan properti data dengan tipenya
    private lateinit var etBarang: EditText
    private lateinit var etType: EditText
    private lateinit var etWaktu: EditText
    private lateinit var btnTambah: Button
    private lateinit var sqliteHelper: SQLiteHelper



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO : Menetepakan konten view dengan isinya ialah layout activity_add_barang
        setContentView(R.layout.activity_add_barang)
        //TODO : Menggunakan class SqLiteHelper -> sqliteHelper
        sqliteHelper = SQLiteHelper(this)
        //TODO : Jalankan fynction saat on create

        initView()

        //TODO : Ketika btnTambah diklik maka menuju ke class MainActivity dan mengakiri aktivitas saat ini
        btnTambah.setOnClickListener {
            addBarang()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    //TODO : Membuat fungsi addBarang
    private fun addBarang() {
        //TODO : menangkapap data text dari component edit text
        val barang = etBarang.text.toString()
        val type = etType.text.toString()
        val waktu = etWaktu.text.toString()

        //TODO : jika salah satu atau semua kosong dari component edit text
        if (barang.isEmpty() || type.isEmpty() || waktu.isEmpty()) {
            //TODO : membuat toast denga text "Mohon isi field "
            Toast.makeText(this, "Mohon isi field ", Toast.LENGTH_SHORT).show()
        } else {
            //TODO : Mengisikan controktur pada class BarangModel -> brg
            val brg = BarangModel(nama = barang, type = type, tanggal = waktu)
            //TODO : jalankan perintah insertBarang(brg) pada sqliteHelper
            val status = sqliteHelper.insertBarang(brg)
            //TODO : periksa apakah  insert barang berhasil
            if (status > -1) {
                Toast.makeText(this, "Barang ditambahkan....", Toast.LENGTH_SHORT).show()
                clearText()

            } else {
                Toast.makeText(this, "Barang tidak disimpan", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //TODO : Membuat method clearText untuk membersihkan text yang ada
    private fun clearText() {
        etBarang.setText("")
        etType.setText("")
        etWaktu.setText("")
        etBarang.requestFocus()
    }


    //TODO : definikan komponen user interface yang digunakan
    private fun initView() {
        etBarang = findViewById(R.id.etBarang)
        etType = findViewById(R.id.etType)
        etWaktu = findViewById(R.id.etWaktu)
        btnTambah = findViewById(R.id.btnTambah)

    }
}