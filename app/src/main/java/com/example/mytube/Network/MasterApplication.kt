package com.example.mytube.Network

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MasterApplication : Application() {
    lateinit var service: RetrofitService
    override fun onCreate() {
        super.onCreate()

        createRetrofit()
    }

    fun createRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(RetrofitService::class.java)
    }

}