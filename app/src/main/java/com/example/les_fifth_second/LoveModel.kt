package com.example.les_fifth_second

import com.google.gson.annotations.SerializedName

data class LoveModel(
    @SerializedName ("fname")//переименования переменной
    val fname:  String,
    @SerializedName ("sname")
    val sname: String,
    val percentage: String,
    val result: String
)
