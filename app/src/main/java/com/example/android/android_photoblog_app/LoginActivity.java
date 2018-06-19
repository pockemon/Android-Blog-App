package com.example.android.android_photoblog_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    private android.widget.EditText loginEmailText;
    private android.widget.EditText loginPassText;
    private android.widget.Button loginBtn;
    private android.widget.Button loginRegBtn;

    private com.google.firebase.auth.FirebaseAuth mAuth;

    private android.widget.ProgressBar loginProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = com.google.firebase.auth.FirebaseAuth.getInstance();

        loginEmailText = (android.widget.EditText) findViewById(com.example.android.android_photoblog_app.R.id.login_email);
        loginPassText = (android.widget.EditText) findViewById(com.example.android.android_photoblog_app.R.id.login_password);
        loginBtn = (android.widget.Button) findViewById(com.example.android.android_photoblog_app.R.id.login_btn);
        loginRegBtn = (android.widget.Button) findViewById(com.example.android.android_photoblog_app.R.id.login_reg_btn);
        loginProgress = (android.widget.ProgressBar) findViewById(com.example.android.android_photoblog_app.R.id.login_progress);


        loginBtn.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {

                String loginEmail = loginEmailText.getText().toString();
                String loginPass = loginPassText.getText().toString();

                if(!android.text.TextUtils.isEmpty(loginEmail) && !android.text.TextUtils.isEmpty(loginPass)) {

                    loginProgress.setVisibility(android.view.View.VISIBLE);

                    mAuth.signInWithEmailAndPassword(loginEmail, loginPass).addOnCompleteListener(new com.google.android.gms.tasks.OnCompleteListener<com.google.firebase.auth.AuthResult>() {
                        @Override
                        public void onComplete(@android.support.annotation.NonNull com.google.android.gms.tasks.Task<com.google.firebase.auth.AuthResult> task) {

                            if(task.isSuccessful()){

                                sendToMain();

                            }
                            else{

                                String errorMessage = task.getException().getMessage();
                                android.widget.Toast.makeText(LoginActivity.this, "Error: "+errorMessage, android.widget.Toast.LENGTH_LONG).show();

                            }

                            loginProgress.setVisibility(android.view.View.INVISIBLE);

                        }
                    });

                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        com.google.firebase.auth.FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null){

            sendToMain();

        }
    }

    private void sendToMain(){

        android.content.Intent mainIntent = new android.content.Intent(LoginActivity.this, MainActivity.class);
        startActivity(mainIntent);
        finish();

    }
}
