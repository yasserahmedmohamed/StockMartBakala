package com.example.yasserahmed.stockmartbakala.Retrofit_get_Notifications;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by yasser ahmed on 1/22/2018.
 */

public class Notification_Responce {
    @SerializedName("message_ar")
   private String message_ar;

    @SerializedName("message_en")
    private String message_en;

    @SerializedName("notifications")
    private ArrayList<Notification_item_class>notifications;

    @SerializedName("success")
    private  String success;

    public String getMessage_ar() {
        return message_ar;
    }

    public void setMessage_ar(String message_ar) {
        this.message_ar = message_ar;
    }

    public String getMessage_en() {
        return message_en;
    }

    public void setMessage_en(String message_en) {
        this.message_en = message_en;
    }

    public ArrayList<Notification_item_class> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<Notification_item_class> notifications) {
        this.notifications = notifications;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
