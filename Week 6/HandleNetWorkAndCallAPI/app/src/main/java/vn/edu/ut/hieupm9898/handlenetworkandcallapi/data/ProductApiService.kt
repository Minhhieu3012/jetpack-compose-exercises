package vn.edu.ut.hieupm9898.handlenetworkandcallapi.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// API Service de lay danh sach san pham
interface ProductApi {
    @GET("products/1")
    suspend fun getProduct(): Product // Trả về danh sách sản phẩm
}

// Ham tao doi tuong Retrofit de thuc hien cac yeu cau API
fun createApi(): ProductApi {
    return Retrofit.Builder()
        // URL co so phai ket thuc bang dau "/"
        .baseUrl("https://dummyjson.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ProductApi::class.java)
}