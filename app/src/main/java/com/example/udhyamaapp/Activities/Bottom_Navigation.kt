package com.example.udhyamaapp.Activities

import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.udhyamaapp.Common.Cart
import com.example.udhyamaapp.R
import com.example.udhyamaapp.Token
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.Exception

class Bottom_Navigation : AppCompatActivity() {
    var cart= Cart()
    var phone:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_bottom__navigation)
            updateToken(FirebaseInstanceId.getInstance().token)

            val navView: BottomNavigationView = findViewById(R.id.nav_view)

            val navController = findNavController(R.id.nav_host_fragment)
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            val appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.navigation_home,
                    R.id.navigation_search,
                    R.id.navigation_cart,
                    R.id.navigation_profile
                )
            )


            navView.setupWithNavController(navController)
            if (intent != null) {
                if (intent.getStringExtra("phone") != null) {
                    phone = intent.getStringExtra("phone")

                }
            }


        }catch (e:Exception)
        {
            println(" Eroor is  mlksdal;s"+e.toString())

        }
    }
    private fun updateToken(token: String?) {

        val data = Token(token, false)// true because this token from server side
        FirebaseDatabase.getInstance().getReference("vaibhav").child("Tokens").child(FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString()).setValue(data)

    }


}
