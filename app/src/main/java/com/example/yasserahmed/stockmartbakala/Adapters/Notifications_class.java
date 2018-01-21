package com.example.yasserahmed.stockmartbakala.Adapters;

/**
 * Created by yasser ahmed on 1/21/2018.
 */

public class Notifications_class {

    private String Offer;
    private String Details;
    private String time;

    public Notifications_class(String offer, String details, String time) {
        Offer = offer;
        Details = details;
        this.time = time;
    }

    public String getOffer() {
        return Offer;
    }

    public String getDetails() {
        return Details;
    }

    public String getTime() {
        return time;
    }
}
