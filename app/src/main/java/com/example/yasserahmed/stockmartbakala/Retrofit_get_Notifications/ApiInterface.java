package com.example.yasserahmed.stockmartbakala.Retrofit_get_Notifications;

import com.example.yasserahmed.stockmartbakala.Success_return;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by yasser ahmed on 1/22/2018.
 */

public interface ApiInterface {
    @GET("users/1/notifications")
    Call<Notification_Responce> getAllNotifications();

    @POST("add_address")
    Call<Success_return> is_successcall(@Query("user_id") int user_id,
                                        @Query("address_type_id") int address_type_id,
                                        @Query("address") String address,
                                        @Query("notes") String notes);


    @POST("complains")
    Call<Success_return> add_complaint(@Query("order_id") int order_id,
                                       @Query("client_id") int client_id,
                                       @Query("name") String name,
                                       @Query("mobile") String mobile,
                                       @Query("email") String email,
                                       @Query("complain") String complain);

}
