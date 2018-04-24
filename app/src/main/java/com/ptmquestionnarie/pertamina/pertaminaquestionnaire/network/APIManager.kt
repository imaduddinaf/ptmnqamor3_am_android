package com.ptmquestionnarie.pertamina.pertaminaquestionnaire.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Imaduddin Al Fikri on 18/04/18.
 */

object APIManager {

    val baseURL: String = "http://10.0.2.2:5000/api/" // localhost:5000

    private var retrofit: Retrofit? = null
    val service: Retrofit
        get() {
            if (retrofit == null) {
                val okHTTPClient = OkHttpClient().newBuilder()
                        .addNetworkInterceptor(StethoInterceptor())
                        .build()

                retrofit = Retrofit.Builder()
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                        .client(okHTTPClient)
                        .baseUrl(baseURL)
                        .build()
            }

            return retrofit!!
        }

    inline fun <reified T> getService(): T {
        return service.create(T::class.java)
    }
}