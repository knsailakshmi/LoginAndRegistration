package com.example.loginandregistration;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Objects;

public class ChangePasswordActivity extends AppCompatActivity {
    private MaterialEditText oldPsw,newPsw,confirmPsw;
    private Button changePsw;
    private FirebaseUser firebaseUser;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChangePasswordActivity.this,MainActivity.class));
            }
        });
        //FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();
        //FirebaseUser
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        //EditText
        oldPsw = findViewById(R.id.oldPassword);
        newPsw = findViewById(R.id.newPassword);
        confirmPsw = findViewById(R.id.confirmPassword);
        //Button
        changePsw = findViewById(R.id.resetPassword);
        //ProgressBar
        progressBar = findViewById(R.id.progressBar);

        changePsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtOldPsw = oldPsw.getText().toString();
                String txtNewPsw = newPsw.getText().toString();
                String txtConfirmPsw = confirmPsw.getText().toString();
                if (txtOldPsw.isEmpty() || txtNewPsw.isEmpty() || txtConfirmPsw.isEmpty()){
                    Toast.makeText(ChangePasswordActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }else if(txtNewPsw.length() <6){
                    Toast.makeText(ChangePasswordActivity.this, "The new password length should be more than 6 character on numbers", Toast.LENGTH_SHORT).show();

                }else if(! txtConfirmPsw.equals(txtNewPsw)){
                    Toast.makeText(ChangePasswordActivity.this, "Confirm password does not match new password", Toast.LENGTH_SHORT).show();
                }else{
                    changePassword(txtOldPsw,txtNewPsw);
                }
            }

        });
    }

    private void changePassword(String txtOldPsw, final String txtNewPsw) {
        progressBar.setVisibility(View.VISIBLE);
        AuthCredential credential = EmailAuthProvider.getCredential(firebaseUser.getEmail(),txtOldPsw);
        firebaseUser.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    firebaseUser.updatePassword(txtNewPsw).addOnCompleteListener(new OnCompleteListener<Void>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                firebaseAuth.signOut();
                               // Toast.makeText(ChangePasswordActivity.this,"Logout",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ChangePasswordActivity.this,MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }else{
                                //Toast.makeText(ChangePasswordActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(ChangePasswordActivity.this,Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }else
                    {
                    progressBar.setVisibility(View.GONE);
                   // Toast.makeText(ChangePasswordActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(ChangePasswordActivity.this,Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_SHORT).show();
                    }

            }
        });
    }
}