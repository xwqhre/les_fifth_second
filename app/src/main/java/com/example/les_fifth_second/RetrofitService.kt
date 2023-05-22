package com.example.les_fifth_second

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService { //место где указываем ссылку

    val retrofit = Retrofit.Builder().baseUrl("https://love-calculator.p.rapidapi.com").
    addConverterFactory(GsonConverterFactory.create()).build()

    var api = retrofit.create(LoveApi::class.java)
}