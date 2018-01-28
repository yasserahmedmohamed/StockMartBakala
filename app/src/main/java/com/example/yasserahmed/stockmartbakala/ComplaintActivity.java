package com.example.yasserahmed.stockmartbakala;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.yasserahmed.stockmartbakala.Retrofit_get_Notifications.ApiInterface;
import com.example.yasserahmed.stockmartbakala.Retrofit_get_Notifications.GetApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComplaintActivity extends AppCompatActivity {

   // Spinner spinner_compl_num;
    EditText total_name,phone_number,email,the_complaint;
Context context;
Activity activity;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        initialize();
    }

    @Override
    protected void onResume() {
        super.onResume();
        add_spin_item();
    }

    void initialize(){
        //spinner_compl_num=(Spinner)findViewById(R.id.spinner_compl_num);
        total_name=(EditText)findViewById(R.id.total_name);
        phone_number=(EditText)findViewById(R.id.phone_number);
        email=(EditText)findViewById(R.id.email);
        the_complaint=(EditText)findViewById(R.id.the_complaint);
        context=this;
        activity=this;
    }

    public void add_spin_item() {
        String[] spin_items = getResources().getStringArray(R.array.location_name);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, spin_items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      //  spinner_compl_num.setAdapter(adapter);
    }



    public void send_complaint(View view) {

        total_name.setError(null);
        phone_number.setError(null);
        email.setError(null);
        the_complaint.setError(null);

        int order_id=1;
        int user_id=1;
        String stotalname=total_name.getText().toString();
        String Sphonum=phone_number.getText().toString();
        String semail=email.getText().toString();
        String scompl=the_complaint.getText().toString();

        if (TextUtils.isEmpty(stotalname) || stotalname.equals(" ")) {
            total_name.setError(getString(R.string.edit_location_name_error));
            total_name.requestFocus();
        }

        if (TextUtils.isEmpty(Sphonum) || Sphonum.equals(" ")) {
            phone_number.setError(getString(R.string.edit_location_name_error));
            phone_number.requestFocus();
        }

        if (TextUtils.isEmpty(semail) || semail.equals(" ")) {
            email.setError(getString(R.string.edit_location_name_error));
            email.requestFocus();
        }

        if (TextUtils.isEmpty(scompl) || scompl.equals(" ")) {
            the_complaint.setError(getString(R.string.edit_location_name_error));
            the_complaint.requestFocus();
        }
else {
            progressDialog = ProgressDialog.show(activity,"",activity.getString(R.string.loading_please_wait), true);
            progressDialog.setCancelable(false);
            progressDialog.show();
            ApiInterface apiInterface = GetApi.getData().create(ApiInterface.class);

            Call<Success_return> Calls =  apiInterface.add_complaint(user_id,order_id,stotalname,Sphonum,semail,scompl);
            Calls.enqueue(new Callback<Success_return>() {
                @Override
                public void onResponse(Call<Success_return> call, Response<Success_return> response) {
                    progressDialog.dismiss();

                    Toast.makeText(context, getString(R.string.location_added)
                            , Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Success_return> call, Throwable t) {

                }
            });
        }


    }
}
