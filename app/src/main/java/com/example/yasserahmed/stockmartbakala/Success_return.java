package com.example.yasserahmed.stockmartbakala;

/**
 * Created by yasser ahmed on 1/24/2018.
 */

public class Success_return {

    String messg_arb;
    String messg_eng;
    String is_success;

    public Success_return(String messg_arb, String messg_eng, String is_success) {
        this.messg_arb = messg_arb;
        this.messg_eng = messg_eng;
        this.is_success = is_success;
    }

    public String getMessg_arb() {
        return messg_arb;
    }

    public String getMessg_eng() {
        return messg_eng;
    }

    public String getIs_success() {
        return is_success;
    }


}
