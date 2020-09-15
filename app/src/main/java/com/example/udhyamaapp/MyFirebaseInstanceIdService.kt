package com.example.udhyamaapp

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService


class MyFirebaseInstanceService : FirebaseInstanceIdService() {

    override fun onTokenRefresh() {
        // Get updated InstanceID token.
        val refreshedToken = FirebaseInstanceId.getInstance().token
        FirebaseMessaging.getInstance().subscribeToTopic("all")
        Log.d(TAG, "Refreshed token: " + refreshedToken!!)

        /* If you want to send messages to this application instance or manage this apps subscriptions on the server side, send the Instance ID token to your app server.*/

        sendRegistrationToServer(refreshedToken)
    }

    private fun sendRegistrationToServer(refreshedToken: String) {
        val token = Token(refreshedToken, false)// false because this token from client side
      //  FirebaseDatabase.getInstance().getReference("vaibhav").child("Tokens").child(FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString().removePrefix("+91")).setValue(token);


        Log.e("TOKEN ", refreshedToken)
    }

    companion object {

        private val TAG = "MyFirebaseInstanceServi"
    }
}
