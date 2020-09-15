package com.example.udhyamaapp.Adpaters

import android.content.Intent
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.udhyamaapp.R

import androidx.recyclerview.widget.RecyclerView
import com.example.udhyamaapp.*
import com.example.udhyamaapp.Activities.FoodList
import com.example.udhyamaapp.Activities.SubCategory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cart_layout.view.*
import android.widget.AdapterView.OnItemClickListener
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.android.synthetic.main.choose_adress.view.*
import android.widget.CheckBox




class CheckoutAddressadapter(val adresslist: ArrayList<Address>) : RecyclerView.Adapter<CheckoutAddressadapter.ViewHolder>() {

    private var lastselectedposition:Int=0
    private var mListener: OnItemClickListener? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(adresslist[position], mListener!!)

    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)


    }

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.choose_adress, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return adresslist.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var lastChecked: CheckBox? = null
        private var lastCheckedPos = 0

        fun bindItems(address: Address, listener: OnItemClickListener) {


            itemView.adressname.text=address.addressname

            itemView.fulladdress.text=address.full_address

            if (position === 0  && itemView.adressname.isChecked()) {
                lastChecked = itemView.adressname
                lastCheckedPos = 0
            }
    //
    //            itemView.adressname.setOnClickListener(View.OnClickListener { v ->
    //                val cb = v as CheckBox
    //                var clickedPos:String? =null
    //                    clickedPos=(cb.tag as Int).toString()
    //
    //                if (cb.isChecked) {
    //                    if (lastChecked != null) {
    //                        lastChecked!!.setChecked(false)
    //                    }
    //
    //                    lastChecked = cb
    //                    lastCheckedPos = clickedPos!!.toInt()
    //                } else
    //                    lastChecked = null
    //            })
            itemView.setOnClickListener (object :View.OnClickListener
            {

                override fun onClick(v: View?) {



                    if(listener!=null)
                    {
                        var position=adapterPosition
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(position)

                        }
                    }

                }
            }

            )




        }
    }
}
