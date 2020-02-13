package com.e.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_holder.view.*

class DataAdapter(var context: Context ,var data: ArrayList<Data>) : RecyclerView.Adapter<DataAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.view_holder, parent, false)
        return Holder(view)
    }
    override fun getItemCount(): Int {
        return data.size
    }
    override fun onBindViewHolder(holder:Holder, position: Int) {
        holder.tvBrand?.text = data[position].brand
        holder.tvYear?.text = data[position].constractionYear
        holder.tvIsUsed?.text = data[position].isUsed.toString()
        Glide.with(context).load(data[position].imageUrl).into(holder.img);
    }
    class Holder (view: View) : RecyclerView.ViewHolder(view) {
        val tvBrand = view.tvBrand
        val tvYear = view.tvYear
        val tvIsUsed = view.tvIsUsed
        val img = view.img
        val mView = view
    }


}