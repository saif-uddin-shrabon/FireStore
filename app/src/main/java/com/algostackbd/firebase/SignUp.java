package com.algostackbd.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUp extends AppCompatActivity {

   EditText regName, regUsername, regEmail, regPhoneNo, regPassword;
    Button signbtn, tologin;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        regName = findViewById(R.id.full_name);
        regUsername = findViewById(R.id.userNameI);
        regEmail = findViewById(R.id.mail);
        regPhoneNo = findViewById(R.id.numberI);
        regPassword = findViewById(R.id.spassID);
        tologin = findViewById(R.id.tologin);
        signbtn = findViewById(R.id.signBbtn);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        progressDialog = new ProgressDialog(this);

        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname = regName.getText().toString();
                String username = regUsername.getText().toString();
                String email = regEmail.getText().toString().trim();
                String phoneNumber =  regPhoneNo.getText().toString();
                String password = regPassword.getText().toString();

                progressDialog.show();

                firebaseAuth.createUserWithEmailAndPassword(email,password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {

                                firebaseFirestore.collection("Users").document(FirebaseAuth.getInstance().getUid())
                                        .set(new UserModel(fullname,username,phoneNumber,email));

                                progressDialog.cancel();

                                Intent myintent = new Intent(SignUp.this, MainActivity.class);
                                startActivity(myintent);
                                finish();

                                Toast.makeText(SignUp.this, "Registation Succesfull", Toast.LENGTH_LONG).show();


                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                progressDialog.cancel();
                                Toast.makeText(SignUp.this, "Registation Failed", Toast.LENGTH_LONG).show();


                            }
                        });
            }
        });


        tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myintent = new Intent(SignUp.this, MainActivity.class);
                startActivity(myintent);
                finish();

            }
        });






    }
}