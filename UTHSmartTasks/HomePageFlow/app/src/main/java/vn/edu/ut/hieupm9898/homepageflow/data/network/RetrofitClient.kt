package vn.edu.ut.hieupm9898.homepageflow.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Singleton object de khoi tao va cung cap retrofit service
object RetrofitClient {
    // URL goc cua API
    private const val BASE_URL = "https://amock.io/"

    // Khoi tao Retrofit
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // dung gson
            .build()
    }

    // cung cap 1 instance cua API service
    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}