package com.example.yasserahmed.stockmartbakala;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.yasserahmed.stockmartbakala.Addresses.Addresses;
import com.example.yasserahmed.stockmartbakala.Retrofit_get_Notifications.ApiInterface;
import com.example.yasserahmed.stockmartbakala.Retrofit_get_Notifications.GetApi;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jaeger.library.StatusBarUtil;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pulling extends AppCompatActivity implements OnMapReadyCallback {
    SupportMapFragment mapFragment;
    Context context;
    ProgressDialog progressDialog;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulling);
        StatusBarUtil.setTransparent(Pulling.this);

        activity = this;
        context = this;
        initialize();
    }


    void initialize() {
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.myplace);
        mapFragment.getMapAsync(this);

    }

    public void pay_imediate(View view) {
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.addrmap);
    }


    //get place name from latitude , longtude
    public String GetLocalityName(double lat, double lng) {


        Geocoder gcd = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = null;
        String add = "";
        try {
            addresses = gcd.getFromLocation(lat, lng, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (addresses.size() > 0) {
            Address obj = addresses.get(0);
            add = obj.getAddressLine(0);
            add = add + "\n" + obj.getAdminArea();
            add = add + "\n" + obj.getCountryName();
        } else {
            Toast.makeText(context, R.string.problem_get_name_loc, Toast.LENGTH_SHORT).show();
        }

        return add;
    }

    List<Addresses.AddressesBean> all_addresses;

    //start showing map and put marker
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        progressDialog = ProgressDialog.show(activity, "", activity.getString(R.string.loading_please_wait), true);
        progressDialog.setCancelable(false);
        progressDialog.show();

        ApiInterface apiInterface = GetApi.getData().create(ApiInterface.class);
        Call<Addresses> call = apiInterface.get_addresses(1);
    //get all locations for saved user
        call.enqueue(new Callback<Addresses>() {
            @Override
            public void onResponse(Call<Addresses> call, Response<Addresses> response) {
                progressDialog.dismiss();
                all_addresses = response.body().getAddresses();
                MarkerOptions markerOptions;
                for (int i = 0; i < all_addresses.size(); i++) {
                    if (all_addresses.get(i).getLat() != 0 || all_addresses.get(i).getLng() != 0) {
                        LatLng latLng = new LatLng(all_addresses.get(i).getLat(), all_addresses.get(i).getLng());
                        markerOptions = new MarkerOptions().position(latLng).title(GetLocalityName(all_addresses.get(i).getLat(), all_addresses.get(i).getLng()))
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
                        googleMap.addMarker(markerOptions);
                    }
                }
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(all_addresses.get(all_addresses.size() - 1).getLat(), all_addresses.get(all_addresses.size() - 1).getLng()), 12.0f));


            }

            @Override
            public void onFailure(Call<Addresses> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, R.string.try_again, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
