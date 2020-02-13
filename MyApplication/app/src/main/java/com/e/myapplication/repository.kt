package com.e.myapplication

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class repository{
    val liveUserResponse: MutableLiveData<ResponseModel> = MutableLiveData()
    val networkError :MutableLiveData<Boolean> = MutableLiveData()
    val showLoading :MutableLiveData<Boolean> = MutableLiveData()


    companion object Factory {
        var gson = GsonBuilder().setLenient().create()
        fun create(): ApiRequest{
            Log.e("retrofit","create")
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(APIUrl.BASE_URL)
                .build()
            return retrofit.create(ApiRequest::class.java)
        }
    }
    fun loadData(page :Int): MutableLiveData<ResponseModel>? {
        showLoading.value = true
        val retrofitCall  = create().getCars(page)
        retrofitCall.enqueue(object : Callback<ResponseModel> {
            override fun onFailure(call: Call<ResponseModel>, t: Throwable?) {
                Log.e("onError:", t?.message.toString())

                networkError.value = true
                showLoading.value = false


            }
            override fun onResponse(call: Call<ResponseModel>, response: retrofit2.Response<ResponseModel>) {
                showLoading.value = false
                val list  = response.body()?.data
                if (list != null) {
                    for (i in list.iterator()){
                        Log.e("onResponse:", i.brand)
                    }
                    liveUserResponse?.value = response.body()
                }

            }
        })
        return liveUserResponse
    }
}