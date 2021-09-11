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
//        Stetho.initializeWithDefaults(this)
        createRetrofit()
    }

    fun createRetrofit() {
//        // 1. Interceptor -> 통신을 가로채서
//        val header = Interceptor {
//            val original = it.request() // 2. 나가려던 통신을 잡아둔다
//            // 로그인이 된 경우에
//            if(checkIsLogin()){
//                // 토큰을 받아오겠다
//                // ?.let --> null이 아닌경우에 let{...} ...하겠다
//                getUserToken()?.let { token ->
//                    val request = original.newBuilder() // 3. 잡아둔 통신을 개조한다
//                        .header("Authorization", "token "+token)
//                        .build()
//                    it.proceed(request)
//                }
//            }else{
//                it.proceed(original)
//            }
//        }
//
//        val client = OkHttpClient.Builder()
//            .addInterceptor(header)
//            .addNetworkInterceptor(StethoInterceptor())
//            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(RetrofitService::class.java)
    }

//    fun checkIsLogin():Boolean {
//        val sp = getSharedPreferences("login_sp", Context.MODE_PRIVATE)
//        val token = sp.getString("login_sp","null")
//        if(token != "null") return true
//        else return false
//    }
//
//    fun getUserToken(): String? {
//        val sp = getSharedPreferences("login_sp", Context.MODE_PRIVATE)
//        val token = sp.getString("login_sp","null")
//        if(token == "null") return null
//        else return token
//    }
}