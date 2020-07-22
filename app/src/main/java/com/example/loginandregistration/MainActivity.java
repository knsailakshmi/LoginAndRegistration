package com.example.loginandregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;

public class MainActivity extends AppCompatActivity {

    private MaterialEditText email, password;
    private ProgressBar progressBar;
    private TextView forgetPassword;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Buttons
        Button registerBtn = findViewById(R.id.register);
        Button loginBtn = findViewById(R.id.login);
        //EditText
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        //Progress Bar
        progressBar = findViewById(R.id.progressBar);
        //TextView
        forgetPassword = findViewById(R.id.forget);
        //FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = email.getText().toString();
                String txt_password =  password.getText().toString();
                if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                    Toast.makeText(MainActivity.this,"All fields are required",Toast.LENGTH_SHORT).show();
                }
                else{
                    login(txt_email,txt_password);
                }
            }
        });
    forgetPassword.setOnClickListener(new View.OnClickListener(){
        public void onClick(View v){
            startActivity(new Intent(MainActivity.this,ForgetPasswordActivity.class));
        }
    });
    }

    private void login(String txt_email,String txt_password) {
     progressBar.setVisibility(View.VISIBLE);
     firebaseAuth.signInWithEmailAndPassword(txt_email,txt_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
         @Override
         public void onComplete(@NonNull Task<AuthResult> task) {
             progressBar.setVisibility(View.GONE);
             if(task.isSuccessful()){
                 Intent intent= new Intent(MainActivity.this,StartActivity.class);
                 intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                 startActivity(intent);
                 finish();
             }
            else {
                Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
             }
         }
     });
    }
}
