package com.algostackbd.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText emailF, passF;
    Button loginBtn,signup,reset;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reset = findViewById(R.id.resetPass);
        emailF = findViewById(R.id.emailID);
        passF = findViewById(R.id.passID);
        loginBtn = findViewById(R.id.logIn);
        signup = findViewById(R.id.signup);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailF.getText().toString().trim();
                String passwoerd = passF.getText().toString();

                progressDialog.show();

                firebaseAuth.signInWithEmailAndPassword(email,passwoerd)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                progressDialog.cancel();

                                Intent myintent = new Intent(MainActivity.this, Profile.class);
                                startActivity(myintent);
                                finish();
                                Toast.makeText(MainActivity.this,"Login Successfull", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.cancel();
                                Toast.makeText(MainActivity.this,"Login Failed", Toast.LENGTH_SHORT).show();

                            }
                        });

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.setTitle("Email Sendin...");
                progressDialog.show();

                firebaseAuth.sendPasswordResetEmail(emailF.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressDialog.cancel();
                                Toast.makeText(MainActivity.this,"Send Email", Toast.LENGTH_SHORT);

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.cancel();
                                Toast.makeText(MainActivity.this,"Email Send Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MainActivity.this, SignUp.class);
                startActivity(myintent);
                finish();
            }
        });



    }
}