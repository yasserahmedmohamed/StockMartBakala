package com.example.yasserahmed.stockmartbakala.Retrofit_get_Notifications;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yasser ahmed on 1/22/2018.
 */

public class GetApi {

    public static final String BASE_URL = "http://1024-apps.com/mart/api/";
    private static Retrofit retrofit = null;


    public static Retrofit getData() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
