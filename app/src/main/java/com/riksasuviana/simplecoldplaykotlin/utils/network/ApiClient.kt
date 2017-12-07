package com.riksasuviana.simplecoldplaykotlin.utils.network

import com.riksasuviana.simplecoldplaykotlin.utils.helper.CompanionHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by riksa on 25/11/2017.
 */
class ApiClient {
    companion object {
        fun request(): Retrofit {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client:OkHttpClient = OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .readTimeout(300, TimeUnit.SECONDS)
                    .connectTimeout(300, TimeUnit.SECONDS)
                    .build()

            return Retrofit.Builder()
                    .baseUrl(CompanionHelper.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                    .client(client)
                    .build()
        }
    }
}