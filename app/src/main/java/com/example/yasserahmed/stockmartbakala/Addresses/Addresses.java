package com.example.yasserahmed.stockmartbakala.Addresses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yasser ahmed on 1/29/2018.
 */

public class Addresses {


    /**
     * message_ar : تمت العملية
     * message_en : process completed
     * addresses : [{"id":1,"user_id":1,"lat":0,"lng":0,"address_type_id":1,"address":"dsa","notes":"dsa","created_at":"2018-01-17 00:00:00","updated_at":"2018-01-17 00:00:00"},{"id":15,"user_id":1,"lat":0,"lng":0,"address_type_id":1,"address":"new cairo","notes":"1","created_at":"2018-01-24 12:58:19","updated_at":"2018-01-24 12:58:19"},{"id":16,"user_id":1,"lat":0,"lng":0,"address_type_id":1,"address":"new cairo","notes":"1","created_at":"2018-01-24 13:51:57","updated_at":"2018-01-24 13:51:57"},{"id":17,"user_id":1,"lat":0,"lng":0,"address_type_id":1,"address":"new cairo","notes":"1","created_at":"2018-01-24 14:06:22","updated_at":"2018-01-24 14:06:22"},{"id":18,"user_id":1,"lat":0,"lng":0,"address_type_id":1,"address":"test 1","notes":"just for test","created_at":"2018-01-24 14:24:55","updated_at":"2018-01-24 14:24:55"},{"id":19,"user_id":1,"lat":0,"lng":0,"address_type_id":1,"address":"test2","notes":"just for test","created_at":"2018-01-24 15:05:50","updated_at":"2018-01-24 15:05:50"},{"id":20,"user_id":1,"lat":0,"lng":0,"address_type_id":0,"address":"test 3","notes":"just for test 3","created_at":"2018-01-24 15:14:55","updated_at":"2018-01-24 15:14:55"},{"id":21,"user_id":1,"lat":0,"lng":0,"address_type_id":0,"address":"test 4","notes":"just for test 4","created_at":"2018-01-24 15:15:42","updated_at":"2018-01-24 15:15:42"},{"id":22,"user_id":1,"lat":0,"lng":0,"address_type_id":0,"address":"test 5","notes":"test","created_at":"2018-01-24 15:21:09","updated_at":"2018-01-24 15:21:09"},{"id":23,"user_id":1,"lat":0,"lng":0,"address_type_id":0,"address":"test 5","notes":"test5","created_at":"2018-01-24 15:22:04","updated_at":"2018-01-24 15:22:04"},{"id":24,"user_id":1,"lat":0,"lng":0,"address_type_id":1,"address":"egypt cairo","notes":"test finish","created_at":"2018-01-24 15:32:52","updated_at":"2018-01-24 15:32:52"},{"id":25,"user_id":1,"lat":0,"lng":0,"address_type_id":0,"address":"test done","notes":"just for test","created_at":"2018-01-24 15:34:07","updated_at":"2018-01-24 15:34:07"},{"id":26,"user_id":1,"lat":0,"lng":0,"address_type_id":1,"address":"test 6","notes":"done","created_at":"2018-01-24 15:38:18","updated_at":"2018-01-24 15:38:18"},{"id":27,"user_id":1,"lat":0,"lng":0,"address_type_id":1,"address":"fdt","notes":"dd","created_at":"2018-01-24 16:34:18","updated_at":"2018-01-24 16:34:18"},{"id":28,"user_id":1,"lat":0,"lng":0,"address_type_id":0,"address":"سميني","notes":"ينبنب","created_at":"2018-01-24 17:28:12","updated_at":"2018-01-24 17:28:12"},{"id":29,"user_id":1,"lat":0,"lng":0,"address_type_id":0,"address":"سميني","notes":"ينبنب","created_at":"2018-01-24 17:28:14","updated_at":"2018-01-24 17:28:14"},{"id":30,"user_id":1,"lat":0,"lng":0,"address_type_id":0,"address":"سميني","notes":"ينبنب","created_at":"2018-01-24 17:28:46","updated_at":"2018-01-24 17:28:46"},{"id":31,"user_id":1,"lat":0,"lng":0,"address_type_id":0,"address":"سميني","notes":"ينبنب","created_at":"2018-01-24 17:28:47","updated_at":"2018-01-24 17:28:47"},{"id":32,"user_id":1,"lat":0,"lng":0,"address_type_id":0,"address":"سميني","notes":"ينبنب","created_at":"2018-01-24 17:28:47","updated_at":"2018-01-24 17:28:47"},{"id":35,"user_id":1,"lat":1,"lng":1,"address_type_id":1,"address":"test 1","notes":"just for test","created_at":"2018-01-29 14:11:20","updated_at":"2018-01-29 14:11:20"},{"id":36,"user_id":1,"lat":1.12314932452,"lng":1.213213,"address_type_id":1,"address":"test 2","notes":"just for test","created_at":"2018-01-29 14:22:08","updated_at":"2018-01-29 14:22:08"},{"id":37,"user_id":1,"lat":30.0590769,"lng":31.3555657,"address_type_id":0,"address":"yasser test finish","notes":"yasser test","created_at":"2018-01-29 14:22:40","updated_at":"2018-01-29 14:22:40"},{"id":38,"user_id":1,"lat":30.0590981,"lng":31.3555599,"address_type_id":0,"address":"yasser test finish 2","notes":"test","created_at":"2018-01-29 14:26:09","updated_at":"2018-01-29 14:26:09"},{"id":39,"user_id":1,"lat":30.0590984,"lng":31.3555538,"address_type_id":0,"address":"yasser test 3","notes":"no comment","created_at":"2018-01-29 14:28:04","updated_at":"2018-01-29 14:28:04"}]
     * success : yes
     */

    @SerializedName("message_ar")
    private String message_ar;
    @SerializedName("message_en")
    private String message_en;
    @SerializedName("success")
    private String success;
    @SerializedName("addresses")
    private List<AddressesBean> addresses;

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

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<AddressesBean> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressesBean> addresses) {
        this.addresses = addresses;
    }

    public static class AddressesBean {
        /**
         * id : 1
         * user_id : 1
         * lat : 0
         * lng : 0
         * address_type_id : 1
         * address : dsa
         * notes : dsa
         * created_at : 2018-01-17 00:00:00
         * updated_at : 2018-01-17 00:00:00
         */



        @SerializedName("id")
        private int id;
        @SerializedName("user_id")
        private int user_id;
        @SerializedName("lat")
        private double lat;
        @SerializedName("lng")
        private double lng;
        @SerializedName("address_type_id")
        private int address_type_id;
        @SerializedName("address")
        private String address;
        @SerializedName("notes")
        private String notes;
        @SerializedName("created_at")
        private String created_at;
        @SerializedName("updated_at")
        private String updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(int lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(int lng) {
            this.lng = lng;
        }

        public int getAddress_type_id() {
            return address_type_id;
        }

        public void setAddress_type_id(int address_type_id) {
            this.address_type_id = address_type_id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }
}
