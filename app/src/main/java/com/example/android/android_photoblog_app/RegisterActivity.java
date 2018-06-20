package com.example.android.android_photoblog_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private android.widget.EditText reg_email_field;
    private android.widget.EditText reg_pass_field;
    private android.widget.EditText reg_pass_confirm_field;
    private android.widget.Button reg_btn;
    private android.widget.Button reg_login_btn;
    private android.widget.ProgressBar reg_progress;

    private com.google.firebase.auth.FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = com.google.firebase.auth.FirebaseAuth.getInstance();

        reg_email_field = (android.widget.EditText) findViewById(com.example.android.android_photoblog_app.R.id.reg_email);
        reg_pass_field = (android.widget.EditText) findViewById(com.example.android.android_photoblog_app.R.id.reg_pass);
        reg_pass_confirm_field = (android.widget.EditText) findViewById(com.example.android.android_photoblog_app.R.id.reg_confirmpass);
        reg_btn = (android.widget.Button) findViewById(com.example.android.android_photoblog_app.R.id.reg_btn);
        reg_login_btn = (android.widget.Button) findViewById(com.example.android.android_photoblog_app.R.id.reg_login_btn);
        reg_progress = (android.widget.ProgressBar) findViewById(com.example.android.android_photoblog_app.R.id.reg_progress);

        reg_btn.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {

                String regEmail = reg_email_field.getText().toString();
                String regPass = reg_pass_field.getText().toString();
                String confirm_regPass = reg_pass_confirm_field.getText().toString();

                if(!android.text.TextUtils.isEmpty(regEmail) && !android.text.TextUtils.isEmpty(regPass) && !android.text.TextUtils.isEmpty(confirm_regPass)) {

                    if(regPass.equals(confirm_regPass)){

                        reg_progress.setVisibility(android.view.View.VISIBLE);

                        mAuth.createUserWithEmailAndPassword(regEmail,regPass).addOnCompleteListener(new com.google.android.gms.tasks.OnCompleteListener<com.google.firebase.auth.AuthResult>() {
                            @Override
                            public void onComplete(@android.support.annotation.NonNull com.google.android.gms.tasks.Task<com.google.firebase.auth.AuthResult> task) {

                                if(task.isSuccessful()){

                                    sendToMain();

                                }
                                else{

                                    String errorMessage = task.getException().getMessage();
                                    android.widget.Toast.makeText(RegisterActivity.this, "Error: "+errorMessage, android.widget.Toast.LENGTH_LONG).show();

                                }

                                reg_progress.setVisibility(android.view.View.INVISIBLE);
                            }
                        });

                    }

                    else{
                        android.widget.Toast.makeText(RegisterActivity.this, "Confirm Password and password field doesn't match", android.widget.Toast.LENGTH_LONG).show();

                    }

                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        com.google.firebase.auth.FirebaseUser CurrentUser = mAuth.getCurrentUser();

        if(CurrentUser !=null){
            sendToMain();
        }
    }

    private void sendToMain(){

        android.content.Intent mainIntent = new android.content.Intent(RegisterActivity.this, MainActivity.class);
        startActivity(mainIntent);
        finish();

    }
}
