package com.example.udhyamaapp.Adpaters
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.udhyamaapp.Activities.PastOrderBill
import com.example.udhyamaapp.PastOrdersDetail
import com.example.udhyamaapp.R
import kotlinx.android.synthetic.main.pastorder.view.*
import java.io.File
import java.sql.Timestamp


class PastOrderAdapter(val pastorderlist: ArrayList<PastOrdersDetail>) : RecyclerView.Adapter<PastOrderAdapter.ViewHolder>() {

     override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(pastorderlist[position])

    }

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.pastorder, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return pastorderlist.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(pastOrdersDetail: PastOrdersDetail) {

            itemView.orderdate.text=pastOrdersDetail.date

            itemView.orderotp.text=pastOrdersDetail.otp
            itemView.orderstatus.text=pastOrdersDetail.status
            itemView.Orderprice.text=pastOrdersDetail.total
            itemView.totalitems.text=pastOrdersDetail.itemtotal

            itemView.orderidno.text=pastOrdersDetail.timestamp.toString()

            itemView.setOnClickListener {



                    var cnt= itemView.context

                    var intent=Intent(cnt,PastOrderBill::class.java)

                    intent.putExtra("timestamp",pastOrdersDetail.timestamp)

                cnt.startActivity(intent)


            }




        }
    }



}