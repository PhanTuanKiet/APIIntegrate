package com.example.apiintegrate.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class RetrofitClient {
    companion object {
        var retrofit : Retrofit? = null

        fun getClient (baseUrl: String): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }
}