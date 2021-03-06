package com.example.yasserahmed.stockmartbakala;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.service.notification.StatusBarNotification;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yasserahmed.stockmartbakala.Retrofit_get_Notifications.ApiInterface;
import com.example.yasserahmed.stockmartbakala.Retrofit_get_Notifications.GetApi;
import com.example.yasserahmed.stockmartbakala.Retrofit_get_Notifications.Notification_Responce;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jaeger.library.StatusBarUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewaddress extends AppCompatActivity implements OnMapReadyCallback {

    Spinner spinner_loc_kind;
    EditText edit_location_name;
    EditText edit_add_more_details;
    Context context;
    double lat, lng;
    // ProgressDialog progressDialog;
    public static final int MY_PERMISSIONS_REQUEST_GET_LOCATION = 1,
            MY_PERMISSIONS_REQUEST_START_GPS = 2;
    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    Activity activity;
    int address_type;
    AlertDialog.Builder alertDialog;
    DialogInterface dialogif;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_newaddress);
       ///////////////////////
        StatusBarUtil.setTransparent(AddNewaddress.this);
        TextView tv_appBarTitl = findViewById(R.id.appBarTitle);
        tv_appBarTitl.setText(R.string.add_new_address);
        initialize();
        ///////////
        address_type = 0;

        spinner_loc_kind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                address_type = i;


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    //add items to spinner
    public void add_spin_item() {

        String[] spin_items = getResources().getStringArray(R.array.location_name);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, spin_items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_loc_kind.setAdapter(adapter);


    }

    //see if gps is working or not
    public void CheckGpsStatus() {

        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        assert locationManager != null;
        boolean GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!GpsStatus) {

            showSettingsAlert();
        } else {
            mapFragment.getMapAsync(this);

        }

    }

    //initialize and tied to XML
    void initialize() {
        context = this;
        activity = this;

        Toolbar customToolbar = findViewById(R.id.c_toolbar);
        setSupportActionBar(customToolbar);

        spinner_loc_kind = (Spinner) findViewById(R.id.spinner_loc_kind);
        edit_location_name = (EditText) findViewById(R.id.edit_location_name);
        edit_add_more_details = (EditText) findViewById(R.id.edit_add_more_details);
        dialogif = null;
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.addrmap);

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_GET_LOCATION);
            }
        } else {
            CheckGpsStatus();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        add_spin_item();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.addrmap);


    }

//show alert to ask for open GPS
    public void showSettingsAlert() {
        alertDialog = new AlertDialog.Builder(context);

        alertDialog.setTitle(R.string.gps_setting);

        // Setting Dialog Message
        alertDialog.setMessage(R.string.open_gps_setting);

        // Setting Icon to Dialog
        //alertDialog.setIcon(R.drawable.delete);

        alertDialog.setPositiveButton(R.string.setting, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(intent, MY_PERMISSIONS_REQUEST_START_GPS);
                //      dialogif.cancel();

            }
        });

        alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialogif = dialog;
                dialog.cancel();
            }
        });

        alertDialog.show();

    }

    //get latitude , longtiude of current location
    public Location getmyLatlang() {

        Location lastlocation = null;

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }

        if (locationManager != null) {
            lastlocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        if (lastlocation == null)
            if (locationManager != null) {
                lastlocation = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
            }


        return lastlocation;
    }

    // get location name of latitude , longtiude of current location
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
            Toast.makeText(context, R.string.no_internet, Toast.LENGTH_SHORT).show();
        }

        return add;
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MY_PERMISSIONS_REQUEST_START_GPS) {
            String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            if (provider != null) {
                if (dialogif != null) {
                    dialogif.cancel();
                }
                mapFragment.getMapAsync(this);

            } else {
                Toast.makeText(context, R.string.we_cant_get_loc, Toast.LENGTH_SHORT).show();
            }
        }


    }


    GoogleMap googleMap;
//start showing map
    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.googleMap = googleMap;
        MovetoLocation();
    }

    MarkerOptions marker;
// move to current location
    public void MovetoLocation() {
        mMap = googleMap;
        final Location curent_location = getmyLatlang();
        if (curent_location != null || mMap != null) {

            LatLng your_location = new LatLng(curent_location.getLatitude(), curent_location.getLongitude());
            lat = curent_location.getLatitude();
            lng = curent_location.getLongitude();
            marker = new MarkerOptions().position(your_location).title(GetLocalityName(your_location.latitude, your_location.longitude));
                            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));

            mMap.moveCamera(CameraUpdateFactory.newLatLng(your_location));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(curent_location.getLatitude(), curent_location.getLongitude()), 12.0f));

            mMap.addMarker(marker);
            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    mMap.clear();
                    marker = new MarkerOptions().position(latLng).title(GetLocalityName(latLng.latitude, latLng.longitude));

                    mMap.addMarker(marker);
                    lat = latLng.latitude;
                    lng = latLng.longitude;

                }
            });
        }
    }

    // save data calling api
    public void call_add_address(View view) {

        edit_location_name.setError(null);
        String string_location_name = edit_location_name.getText().toString();
        String string_add_more_details = edit_add_more_details.getText().toString();


        if (TextUtils.isEmpty(string_add_more_details) || string_add_more_details.equals(" ")) {
            string_add_more_details = "no comment";
        }
        if (TextUtils.isEmpty(string_location_name) || string_location_name.equals(" ")) {
            edit_location_name.setError(getString(R.string.edit_location_name_error));
            edit_location_name.requestFocus();
        } else {
            progressDialog = ProgressDialog.show(activity, "", activity.getString(R.string.loading_please_wait), true);
            progressDialog.setCancelable(false);
            progressDialog.show();

            ApiInterface apiInterface = GetApi.getData().create(ApiInterface.class);

            Call<Success_return> Calls = apiInterface.Add_new_address(1, address_type, string_location_name, string_add_more_details, lat, lng);
            Calls.enqueue(new Callback<Success_return>() {
                @Override
                public void onResponse(Call<Success_return> call, Response<Success_return> response) {
                    progressDialog.dismiss();

                    Toast.makeText(context, getString(R.string.location_added)
                            , Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Success_return> call, Throwable t) {
                    Toast.makeText(context, R.string.problem_get_loc, Toast.LENGTH_SHORT).show();

                }
            });


        }
    }
}