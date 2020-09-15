package com.example.udhyamaapp;



import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


/**
 * Created by vishalverma on 28/01/18.
 */

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAoSxy27s:APA91bGtETyoq23ccmqPIhNqNKxkgznzhsIj8-MO_-WvFs9k5hLLJF_u7rRnMVotxarLocyYw3HbSdKmPQY8WPwzr_pElasQKPumy46nuYGKEDjKShgQqR_LWd0xcXjJbVim4VUdkkjE"

            }
    )
    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body DataMessage body);




}
