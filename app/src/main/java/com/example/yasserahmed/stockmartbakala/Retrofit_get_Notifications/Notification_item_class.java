package com.example.yasserahmed.stockmartbakala.Retrofit_get_Notifications;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yasser ahmed on 1/22/2018.
 */

public class Notification_item_class {
    @SerializedName("id")
    private String id;

    @SerializedName("title_ar")
    private String title_ar;

    @SerializedName("title_en")
    private String title_en;

    @SerializedName("body_ar")
    private String body_ar;

    @SerializedName("body_en")
    private String body_en;

    @SerializedName("created_at")
    private String created_at;

    public Notification_item_class(String id, String title_ar, String title_en, String body_ar, String body_en, String created_at) {
        this.id = id;
        this.title_ar = title_ar;
        this.title_en = title_en;
        this.body_ar = body_ar;
        this.body_en = body_en;
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public String getTitle_ar() {
        return title_ar;
    }

    public String getTitle_en() {
        return title_en;
    }

    public String getBody_ar() {
        return body_ar;
    }

    public String getBody_en() {
        return body_en;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle_ar(String title_ar) {
        this.title_ar = title_ar;
    }

    public void setTitle_en(String title_en) {
        this.title_en = title_en;
    }

    public void setBody_ar(String body_ar) {
        this.body_ar = body_ar;
    }

    public void setBody_en(String body_en) {
        this.body_en = body_en;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
