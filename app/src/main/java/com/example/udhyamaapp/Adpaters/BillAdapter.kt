package com.example.udhyamaapp.Adpaters
import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton
import com.example.udhyamaapp.Activities.Bill
import com.example.udhyamaapp.Food
import com.example.udhyamaapp.R
import com.squareup.picasso.Picasso
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.billfood.view.*
import kotlinx.android.synthetic.main.cart_layout.view.*
import kotlinx.android.synthetic.main.food_item.view.*


class BillAdapter(val imagelist: ArrayList<Food>,var context: Context) : RecyclerView.Adapter<BillAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(imagelist[position])

    }



    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.billfood, parent, false)

        return ViewHolder(v,context)
    }

    //this method is binding the data on the list

    //this method is giving the size of the list
    override fun getItemCount(): Int {

        return imagelist.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View,context1: Context) : RecyclerView.ViewHolder(itemView) {

        var number=0;

        fun bindItems(food: Food) {


            itemView.billitemname.text=food.name

            var fooddiscountprice=food.price!!-(food.price!!.toInt() * food.discount!!.toInt())/100

            itemView.billpriceunit.text="₹ " +fooddiscountprice.toString()
            itemView.billquantity.text=food.cart.toString()

            itemView.billamount.text="₹"+((fooddiscountprice)*food.cart!!.toInt()).toString()

            number+=1



        }


    }


}