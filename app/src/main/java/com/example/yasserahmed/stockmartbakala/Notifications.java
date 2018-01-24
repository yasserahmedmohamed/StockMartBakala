package com.example.yasserahmed.stockmartbakala;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.yasserahmed.stockmartbakala.Adapters.Notifications_Adapters;
import com.example.yasserahmed.stockmartbakala.Adapters.Notifications_class;
import com.example.yasserahmed.stockmartbakala.Retrofit_get_Notifications.ApiInterface;
import com.example.yasserahmed.stockmartbakala.Retrofit_get_Notifications.GetApi;
import com.example.yasserahmed.stockmartbakala.Retrofit_get_Notifications.Notification_Responce;
import com.example.yasserahmed.stockmartbakala.Retrofit_get_Notifications.Notification_item_class;

import java.util.ArrayList;

public class Notifications extends AppCompatActivity {
    ArrayList<Notification_item_class> NotificationsArrayList;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rec_notifications);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        NotificationsArrayList = new ArrayList<>();
        ApiInterface apiInterface= GetApi.getData().create(ApiInterface.class);

      final ProgressDialog  progressDialog = ProgressDialog.show(this,"","please wait...", true);
        progressDialog.setCancelable(false);
        progressDialog.show();

        Call<Notification_Responce> call=apiInterface.getAllNotifications();
        call.enqueue(new Callback<Notification_Responce>() {
            @Override
            public void onResponse(Call<Notification_Responce> call, Response<Notification_Responce> response) {
                progressDialog.dismiss();
                ArrayList<Notification_item_class> notification_item_classes = response.body().getNotifications();

                recyclerView.setAdapter(new Notifications_Adapters(notification_item_classes, R.layout.notificatons_item, getApplicationContext()));

            }

            @Override
            public void onFailure(Call<Notification_Responce> call, Throwable t) {

            }
        });


    }
}
