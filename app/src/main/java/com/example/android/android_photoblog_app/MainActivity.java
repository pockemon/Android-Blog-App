package com.example.android.android_photoblog_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        com.google.firebase.auth.FirebaseUser currentUser = com.google.firebase.auth.FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser == null) {

            // User is signed in
            android.content.Intent home_page = new android.content.Intent(MainActivity.this, LoginActivity.class);
            startActivity(home_page);
            finish();

        }

    }
}
