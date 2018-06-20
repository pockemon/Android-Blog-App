package com.example.android.android_photoblog_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar mToolbar;

    private com.google.firebase.auth.FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = com.google.firebase.auth.FirebaseAuth.getInstance();

        mToolbar = (android.support.v7.widget.Toolbar) findViewById(com.example.android.android_photoblog_app.R.id.main_Toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setTitle("Photo Blog");
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

    //add the menubar to main activity

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {

        switch(item.getItemId()){

            case com.example.android.android_photoblog_app.R.id.action_logout_btn:
                logout();
                return true;

            default:
                return false;

        }
    }

    private void logout(){

        mAuth.signOut();

        //if user log's out then send hime to login page
        android.content.Intent login_page = new android.content.Intent(MainActivity.this, LoginActivity.class);
        startActivity(login_page);
        finish();

    }
}
