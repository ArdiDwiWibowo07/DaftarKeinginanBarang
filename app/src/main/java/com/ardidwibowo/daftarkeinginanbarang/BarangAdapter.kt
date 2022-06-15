package com.ardidwibowo.daftarkeinginanbarang

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BarangAdapter : RecyclerView.Adapter<BarangAdapter.BarangViewHolder>() {
    //TODO : Mendifinisikan data properti dengan tipenya
    private var brgList: ArrayList<BarangModel> = ArrayList()
    private var onClickItem: ((BarangModel) -> Unit)? = null
    private var onClickDeleteItem: ((BarangModel) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    //TODO : membuat fungso addItem dengan paramter Item dengan arrayList dari class BarangModel
    fun addItem(Items: ArrayList<BarangModel>){
        //TODO : properti brglist beriskan Item
        this.brgList = Items
        //TODO : data akan menyesuaiakn perubahan
        notifyDataSetChanged()
    }

    //TODO : membuat fungsi setOnClickItem dengan paramter callback yang berupa unit dari BarangModel
    fun setOnClickItem(callback: (BarangModel) -> Unit){
        //TODO : propeti onClickItem berisikan callback
        this.onClickItem = callback
    }
    //TODO : membuat fungsi setOnClickItem dengan paramter callback yang berupa unit dari BarangModel
    fun setOnClickDeleteItem(callback: (BarangModel) -> Unit){
        //TODO : propeti onClickDeleteItem berisikan callback
        this.onClickDeleteItem = callback
    }

//TODO : Mengunakan view layout yang digunakan ialah card_items_brg
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BarangViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_items_brg, parent, false)
    )

    //TODO : Mengisi value setiap component yang ada pada holder yang akan digunakan
    override fun onBindViewHolder(holder: BarangViewHolder, position: Int) {
        //TODO : Definiskan posisi dari  brgList -> brg
        val brg = brgList[position]
        //TODO membuat holder berdasarakn brg
        holder.bindView(brg)
        //TODO ketika itemView di klik jalanakan onClickItem?.invoke(brg)
        holder.itemView.setOnClickListener {
            onClickItem?.invoke(brg)
        }
        //TODO ketika itemView di klik jalanakan onClickDeleteItem?.invoke(brg)
        holder.beli.setOnClickListener {
            onClickDeleteItem?.invoke(brg)
        }
    }

    //TODO : membuat fungsi getItemCount yang mengembalikan jumlah brgList
    override fun getItemCount(): Int {
        return brgList.size
    }

    class  BarangViewHolder(view:View): RecyclerView.ViewHolder(view){
        //TODO : definsikan component view yang digunakan
        private var nama = view.findViewById<TextView>(R.id.txtNama)
        private var type = view.findViewById<TextView>(R.id.txtTipe)
        private var tanggal = view.findViewById<TextView>(R.id.txtTanggal)
        var beli = view.findViewById<TextView>(R.id.btnBeli)
        //TODO : definsikan component view yang digunakan pada class BarangModel
        fun bindView(brg:BarangModel){
            nama.text = brg.nama
            type.text = brg.type
            tanggal.text = brg.tanggal
        }
    }
}