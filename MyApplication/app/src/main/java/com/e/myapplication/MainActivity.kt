package com.e.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private var page = 1;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        linearLayoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.hasFixedSize()
        getData();
    }


    private fun getData() {
        val viewModel = ViewModelProviders.of(this@MainActivity).get(BViewModel::class.java)
        viewModel.getError()?.observe(this, Observer<Boolean> {showError(it)  })
        viewModel.getCarData(page =page )?.observe(this, Observer<ResponseModel> { it ->

            if (it != null) {
                for (i in it.data.iterator()) {
                    Log.e("list", i.brand)
                }
                recyclerView.adapter = DataAdapter(this@MainActivity, it.data)
            }
        })
    }

   fun showError( isShow:Boolean){
       if(isShow){
           tvError.visibility = View.VISIBLE
       }else{
           tvError.visibility = View.GONE

       }
   }
}
