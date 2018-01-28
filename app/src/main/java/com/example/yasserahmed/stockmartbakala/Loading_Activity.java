package com.example.yasserahmed.stockmartbakala;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wang.avi.AVLoadingIndicatorView;

public class Loading_Activity extends AppCompatActivity {

    AVLoadingIndicatorView avi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_);
        avi=(AVLoadingIndicatorView)findViewById(R.id.loading);
        avi.show();
    }
}
