package com.e.myapplication

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiRequest {

    @GET(APIUrl.CAR)
    fun getCars(
        @Query("page") page: Int
    ):Call<ResponseModel>


    companion object Factory {
        fun create(): ApiRequest {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(APIUrl.BASE_URL)
                .build()

            return retrofit.create(ApiRequest::class.java);
        }
    }
}