package com.example.yasserahmed.stockmartbakala;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuWrapperFactory;
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
    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    Context context;
    public static final int MY_PERMISSIONS_REQUEST_GET_LOCATION = 1, MY_PERMISSIONS_REQUEST_START_GPS = 2;
    ProgressDialog progressDialog;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulling);
        StatusBarUtil.setTransparent(Pulling.this);

        activity = this;
        initialize();
    }


    void initialize() {
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.myplace);
        context = this;
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_GET_LOCATION);
            }
        } else {
            mapFragment.getMapAsync(this);

        }
    }

    public void pay_imediate(View view) {
    }


    public void CheckGpsStatus() {

        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        boolean GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!GpsStatus) {

            showSettingsAlert();
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.addrmap);
        mapFragment.getMapAsync(this);
        CheckGpsStatus();
    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        alertDialog.setTitle("GPS is settings");

        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

        // Setting Icon to Dialog
        //alertDialog.setIcon(R.drawable.delete);

        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(intent, MY_PERMISSIONS_REQUEST_START_GPS);
                dialog.cancel();
            }
        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MY_PERMISSIONS_REQUEST_START_GPS) {
            CheckGpsStatus();


        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case MY_PERMISSIONS_REQUEST_GET_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mapFragment.getMapAsync(this);

                    CheckGpsStatus();
                }
            }
        }


    }

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
            Toast.makeText(context, "There is no problem with local", Toast.LENGTH_SHORT).show();
        }

        return add;
    }

    List<Addresses.AddressesBean> all_addresses;

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        progressDialog = ProgressDialog.show(activity, "", activity.getString(R.string.loading_please_wait), true);
        progressDialog.setCancelable(false);
        progressDialog.show();

        ApiInterface apiInterface = GetApi.getData().create(ApiInterface.class);
        Call<Addresses> call = apiInterface.get_addresses(1);

        call.enqueue(new Callback<Addresses>() {
            @Override
            public void onResponse(Call<Addresses> call, Response<Addresses> response) {
                progressDialog.dismiss();
                all_addresses = response.body().getAddresses();
                MarkerOptions markerOptions;
                for (int i = 0; i < all_addresses.size(); i++) {
                    if(all_addresses.get(i).getLat()!=0||all_addresses.get(i).getLng()!=0){
                        LatLng latLng= new LatLng(all_addresses.get(i).getLat(), all_addresses.get(i).getLng());
                    markerOptions = new MarkerOptions().position(latLng).title(GetLocalityName(all_addresses.get(i).getLat(), all_addresses.get(i).getLng()))
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
                    googleMap.addMarker(markerOptions);}
                }
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(all_addresses.get(all_addresses.size()-1).getLat(),all_addresses.get(all_addresses.size()-1).getLng()), 12.0f));


            }

            @Override
            public void onFailure(Call<Addresses> call, Throwable t) {
                progressDialog.dismiss();
Toast.makeText(context,"there is problem please try again",Toast.LENGTH_SHORT).show();
            }
        });


    }
}
