package com.example.udhyamaapp.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.udhyamaapp.R
import android.widget.EditText
import android.widget.Button
import android.content.Intent
import android.view.View

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import android.widget.Toast
import org.json.JSONObject
import android.app.Activity
import android.preference.PreferenceManager

import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.example.udhyamaapp.Food
import com.example.udhyamaapp.Orderdetail
import com.example.udhyamaapp.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.android.synthetic.main.activity_payment_i.*
import java.util.*
import kotlin.collections.ArrayList


class PaymentI : AppCompatActivity(),PaymentResultListener{
     private val TAG = PaymentI::class.java!!.getSimpleName()
    var itemcount="0"
    var itemtotal="0"
     public override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)

         setContentView(R.layout.activity_payment_i)

         if(intent.extras!=null)
         {
             itemcount=intent.getStringExtra("itemcount")
             itemtotal=intent.getStringExtra("itemtotal")
         }
         /*
         To ensure faster loading of the Checkout form,
          call this method as early as possible in your checkout flow.
         */
         Checkout.preload(applicationContext)

         // Payment button created by you in XML layout

             startPayment() }


     fun startPayment() {
         /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
         val activity = this

         val co = Checkout()

         try {
             val options = JSONObject()
             options.put("name", "Razorpay Corp")
             options.put("description", "Demoing Charges")
             //You can omit the image option to fetch the image from dashboard
             options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
             options.put("currency", "INR")
             options.put("amount", "100")

             val preFill = JSONObject()
             preFill.put("email", "test@razorpay.com")
             preFill.put("contact", "7062134288")

             options.put("prefill", preFill)

             co.open(activity, options)
         } catch (e: Exception) {
             Toast.makeText(activity, "Error in payment: " + e.message, Toast.LENGTH_SHORT)
                 .show()
             e.printStackTrace()
         }

     }
     override fun onPaymentSuccess(razorpayPaymentID: String) {
         try {
             Toast.makeText(this, "Payment Successful: $razorpayPaymentID", Toast.LENGTH_SHORT)
                 .show()
             placeorder()
         } catch (e: Exception) {
             Log.e("error", "Exception in onPaymentSuccess", e)
         }

     }
    override fun onPaymentError(code: Int, response: String) {
        try {
            Toast.makeText(this, "Payment failed: $code $response", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.e("error" , "Exception in onPaymentError", e)
        }

    }

    private fun placeorder()
    {




        try {

            var foodlist = getcartlist()
            if (foodlist.size != 0) {

                var orderdetail = Orderdetail()


                var userdetail: Users? = null
                var phone = FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString()
                    .removePrefix("+91")
                val tsLong = System.currentTimeMillis() / 1000
                val ts = tsLong.toString()

                println(ts)
               var database = FirebaseDatabase.getInstance()

               var myRef = database.getReference("vaibhav")

                var ref = database.getReference("vaibhav")
                println(phone)
                ref.child("user").child(phone).addValueEventListener(object :
                    ValueEventListener {


                    override fun onCancelled(p0: DatabaseError) {
                        Toast.makeText(this@PaymentI, "Error ", Toast.LENGTH_LONG).show()
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {

                        userdetail = dataSnapshot.getValue(Users::class.java)


                        if (userdetail != null) {
                            orderdetail.address = userdetail!!.address
                            orderdetail.comment = edtComment.text.toString()
                            orderdetail.personname = userdetail!!.name

                            orderdetail.foods = foodlist

                            orderdetail.itemtotal = itemcount
                            orderdetail.total = itemtotal

                            var date = Date() // the date instance
                            var calendar = Calendar.getInstance();
                            calendar.setTime(date);
                            var datetoday =
                                calendar.get(Calendar.DAY_OF_MONTH).toString() + " " + (calendar.get(
                                    Calendar.MONTH
                                ) + 1).toString() + " " + calendar.get(Calendar.YEAR)

                            orderdetail.phone = phone
                            orderdetail.status = "Placed"
                            orderdetail.timestamp = ts
                            orderdetail.date = datetoday

                            orderdetail.delvphone=""
                            val randomPIN = (Math.random() * 9000).toInt() + 1000
                            var otp = randomPIN.toString()

                            println("otp is" + otp)
                            orderdetail.otp = otp
                            myRef.child("Requests").child(ts).setValue(orderdetail)


                            Toast.makeText(
                                this@PaymentI,
                                "Order Placed Succesfully",
                                Toast.LENGTH_LONG
                            ).show()

                            var intent = Intent(this@PaymentI, Bill::class.java)
                            intent.putExtra("name", orderdetail.personname)
                            intent.putExtra("date", orderdetail.date)
                            intent.putExtra("totalitem", orderdetail.itemtotal)
                            intent.putExtra("price", orderdetail.total)
                            intent.putExtra("timestamp", orderdetail.timestamp)
                            intent.putExtra("useremail",userdetail!!.email)


                            var arrlist:ArrayList<Food>?=null
                            saveCartList(arrlist)
                            startActivity(intent)


                            finish()


                        }
                    }
                })


            } else {
                Toast.makeText(this, "Your cart is empty ", Toast.LENGTH_LONG).show()

            }

        } catch (e: Exception) {
            Toast.makeText(this, "Error ", Toast.LENGTH_LONG).show()
        }




    }



    private fun getcartlist():ArrayList<Food>
    {
        try {

            val prefs = PreferenceManager.getDefaultSharedPreferences(this)
            val gson = Gson()
            val json = prefs.getString("cartlist", "cart")
            val type = object : TypeToken<ArrayList<Food>>() {

            }.type
            if(json!=null) {
                return gson.fromJson(json, type)
            }
            else
            {

            }
        }catch (e: Exception)
        {
            println(e)
        }
        var arr=ArrayList<Food>()
        return arr

    }
    fun saveCartList(list: ArrayList<Food>?) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = prefs.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        editor.putString("cartlist", json)
        editor.apply()     // This line is IMPORTANT !!!
        println("cartlist stored succesfully")
    }







}
