package com.ardidwibowo.daftarkeinginanbarang

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    //TODO : Mendifinisikan properti data dengan tipenya
    private lateinit var btnTambah : Button
    private lateinit var sqliteHelper : SQLiteHelper
    private lateinit var recyclerView: RecyclerView
    private var adapter: BarangAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO : Menetepakan konten view dengan isinya ialah layout activity main
        setContentView(R.layout.activity_main)

        //TODO : Jalankan fynction saat on create
        initView()
        initRecyleView()


        //TODO : Menggunakan class SqLiteHelper -> sqliteHelper
        sqliteHelper = SQLiteHelper(this)
        //TODO : Memanggil/menjalanka fungsi dari getAllBarang -> brgList
        val brgList = sqliteHelper.getAllBarang()
        //TODO : Memanggil adapter yang boleh null dan menemangkan fungsi addItem dengan mengisikan parameter dengan variabel brgList
        adapter?.addItem(brgList)

        //TODO : Ketika btnTambah diklik maka menuju ke class AddActivity dan mengakiri aktivitas saat ini
        btnTambah.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)

            startActivity(intent)
            finish()
        }

        //TODO : Ketika adapter memanggil setOnclikItemd atau klik datanya  maka akan muncul pop up data nama
        adapter?.setOnClickItem {
            Toast.makeText(this, it.nama, Toast.LENGTH_SHORT).show()
        }

        //TODO : Ketika adapter memanggil setOnClickDeleteItem atau klik tombol delete maka jalankan fungsi deleteBarng denga
        //TODO : dengan diisi parameter id dengan id yang diklik datanya
        adapter?.setOnClickDeleteItem {
            deleteBarang(it.id)

        }
    }

    //TODO : Membuat fungsi getBarang
    private fun getBarang(){
        //TODO : Mengakses sqliteHelper fungsi getALlBarang() -> brgList
        val brgList = sqliteHelper.getAllBarang()
        //TODO : memanggil addItem pada adapter dan diisi parameternya dengan variabel brgList
        adapter?.addItem(brgList)
    }




// TODO : Membuat fungsi deleteBarang dengan paramter Id dengan tipe Integer
    private fun deleteBarang(Id: Int){
    //TODO : Membuat dialogi -> builder
        val builder = AlertDialog.Builder(this)
        //TODO : Membuat text pada dialog
        builder.setMessage("Ingin menghapus barang")
        //TODO : Perintah dalam dialog boleh tidak jadi atau cancel
        builder.setCancelable(true)

        //TODO : Saat menekan postive yang diindikasi button yes maka
        builder.setPositiveButton("yes"){dialog,_->
            dialog.dismiss()
            //TODO : Jalankan perintah deleteBarangId yang ada di sqliteHelper
            sqliteHelper.deleteBarangById(Id)
            //TODO : Jalankan fungsi getBarang
            getBarang()
        }
            //TODO : Merupakan button untuk tidak jadi atau cancel
        builder.setNegativeButton("no"){dialog,_->
            dialog.dismiss()

        }

    //TODO : dialog dibuat dan ditampilkan
        val alert = builder.create()
        alert.show()

    }

    //TODO : Membuat fungsi initRecyleView
    private fun initRecyleView() {

        //TODO : Berisikan mendefinsikan adapter yang beriskabn data data dari class BarangAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = BarangAdapter()
        recyclerView.adapter = adapter

    }
    //TODO : definikan komponen user interface yang digunakan
    private fun initView(){

        btnTambah = findViewById(R.id.btnTambah)

        recyclerView = findViewById(R.id.recyleView)
    }
}