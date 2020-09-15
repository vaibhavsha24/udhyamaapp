package com.example.udhyamaapp.Adpaters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton
import com.example.udhyamaapp.Activities.FoodDetail
import com.example.udhyamaapp.Food
import com.example.udhyamaapp.R
import com.squareup.picasso.Picasso
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.cart_layout.view.*
import kotlinx.android.synthetic.main.food_item.view.*
import kotlinx.android.synthetic.main.searchfood.view.*


class SearchFoodAdapter(val imagelist: ArrayList<Food>,var context: Context) : RecyclerView.Adapter<SearchFoodAdapter.ViewHolder>() {


    private var mListener: FoodListAdapter.OnItemClickListener? = null


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.bindItems(imagelist[position],mListener!!)
        holder.bindItems(imagelist[position])

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)

        fun onDeleteClick(position: Int)

        fun onitemcountchanged(position: Int,number:String)
    }
    fun setOnItemClickListener(listener: FoodListAdapter.OnItemClickListener) {
        mListener = listener
    }


    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.searchfood, parent, false)

        return ViewHolder(v,context)
    }

    //this method is binding the data on the list

    //this method is giving the size of the list
    override fun getItemCount(): Int {

        return imagelist.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View,context1: Context) : RecyclerView.ViewHolder(itemView) {


        fun bindItems(food: Food) {


            Picasso.get().load(food.image).into(itemView.foodimage)
            itemView.foodname.text=food.name

            itemView.setOnClickListener {

               var intent= Intent(itemView.context,FoodDetail::class.java)
                intent.putExtra("foodname",food.name)
                itemView.context.startActivity(intent)

            }



        }


    }


}