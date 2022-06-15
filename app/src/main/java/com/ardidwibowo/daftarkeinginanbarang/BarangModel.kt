package com.ardidwibowo.daftarkeinginanbarang

import java.util.*

//TODO: Membuat data class BarangModel
data class BarangModel (

    //TODO : Mendefiniskan properti class
    var id: Int = getAutoId(),
    var nama: String = "",
    var type: String="",
    var tanggal: String =""
){
    companion object{

        //TODO : Membuat method getAutoId yang mengembalikan angka random
        fun getAutoId():Int{
            val random = Random()
            return random.nextInt()
        }
    }
}
