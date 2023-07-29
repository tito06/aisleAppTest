package com.example.myapplication

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiServices {

    @GET("users/test_profile_list")
    suspend fun getData(
        @Header("Authorization") token:String?,
    ):Response<UserResponse>

    @POST("users/phone_number_login")
    suspend fun getOtp(
        @Body request: OtpRequest
    ):Response<OtpResponse>

    @POST("users/verify_otp")
    suspend fun login(
        @Body request: VerifyOtpRequest
    ):Response<VerifyOtpResponse>
}

object RetrofitHelper {

    val baseUrl = "https://app.aisle.co/V1/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}