package com.e.myapplication

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



 class BViewModel() :ViewModel(){

     val repository =repository()
     fun getCarData(page :Int) : MutableLiveData<ResponseModel>? {
         Log.e("getAndroidData","yes")
         return repository.loadData(page)
     }

     fun getError() :MutableLiveData<Boolean>?{
         return repository.networkError
     }
     fun showLoading() :MutableLiveData<Boolean>?{
        return repository.showLoading
     }
}