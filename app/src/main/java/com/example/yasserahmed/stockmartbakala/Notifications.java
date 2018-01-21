package com.example.yasserahmed.stockmartbakala;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.yasserahmed.stockmartbakala.Adapters.Notifications_Adapters;
import com.example.yasserahmed.stockmartbakala.Adapters.Notifications_class;

import java.util.ArrayList;

public class Notifications extends AppCompatActivity {
    ArrayList<Notifications_class>NotificationsArrayList;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rec_notifications);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
NotificationsArrayList=new ArrayList<>();
        NotificationsArrayList.add(new Notifications_class("عرض الساعه 30 ريال","لديك تنبيه بخصوص العنوان الذي اضفته عرض الساعه لديك تنبيه بخصوص العنوان الذي اضفته عرض الساعه ","12 اكتوبر لعام 2017"));
        NotificationsArrayList.add(new Notifications_class("عرض الساعه 30 ريال","لديك تنبيه بخصوص العنوان الذي اضفته عرض الساعه لديك تنبيه بخصوص العنوان الذي اضفته عرض الساعه ","12 اكتوبر لعام 2017"));
        NotificationsArrayList.add(new Notifications_class("عرض الساعه 30 ريال","لديك تنبيه بخصوص العنوان الذي اضفته عرض الساعه لديك تنبيه بخصوص العنوان الذي اضفته عرض الساعه ","12 اكتوبر لعام 2017"));
        NotificationsArrayList.add(new Notifications_class("عرض الساعه 30 ريال","لديك تنبيه بخصوص العنوان الذي اضفته عرض الساعه لديك تنبيه بخصوص العنوان الذي اضفته عرض الساعه ","12 اكتوبر لعام 2017"));
        NotificationsArrayList.add(new Notifications_class("عرض الساعه 30 ريال","لديك تنبيه بخصوص العنوان الذي اضفته عرض الساعه لديك تنبيه بخصوص العنوان الذي اضفته عرض الساعه ","12 اكتوبر لعام 2017"));
        NotificationsArrayList.add(new Notifications_class("عرض الساعه 30 ريال","لديك تنبيه بخصوص العنوان الذي اضفته عرض الساعه لديك تنبيه بخصوص العنوان الذي اضفته عرض الساعه ","12 اكتوبر لعام 2017"));
        NotificationsArrayList.add(new Notifications_class("عرض الساعه 30 ريال","لديك تنبيه بخصوص العنوان الذي اضفته عرض الساعه لديك تنبيه بخصوص العنوان الذي اضفته عرض الساعه ","12 اكتوبر لعام 2017"));

        recyclerView.setAdapter(new Notifications_Adapters(NotificationsArrayList,R.layout.notificatons_item,getApplicationContext()));
    }
}
