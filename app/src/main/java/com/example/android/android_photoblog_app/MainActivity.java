package com.example.android.android_photoblog_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.content.Intent home_page = new android.content.Intent(MainActivity.this, LoginActivity.class);
        startActivity(home_page);
    }
}
