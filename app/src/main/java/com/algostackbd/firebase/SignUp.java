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
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;


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
                String RegfullName = regName.getText().toString();
                String ReguserName = regUsername.getText().toString();
                String Regemail = regEmail.getText().toString().trim();
                String RegPhone = regPhoneNo.getText().toString();
                String RegPass = regPassword.getText().toString();

                System.out.println("Test Email Before : "+Regemail);
                System.out.println("Test Pass Before : "+RegPass);
                System.out.println("Test RegfullName Before : "+RegfullName);
                System.out.println("Test ReguserName Before : "+ReguserName);
                System.out.println("Test RegPhone Before : "+RegPhone);

                progressDialog.show();

                firebaseAuth.createUserWithEmailAndPassword(Regemail, RegPass)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {

                                System.out.println("Test Email After : "+Regemail);
                                System.out.println("Test Pass After : "+RegPass);

                                firebaseFirestore.collection("Users")
                                        .document(FirebaseAuth.getInstance().getUid())
                                        .set(new userClass(RegfullName,ReguserName,RegPhone,Regemail));

                                Intent myintent = new Intent(SignUp.this, MainActivity.class);
                                startActivity(myintent);
                                finish();
                                Toast.makeText(SignUp.this,"Reg Succesfull", Toast.LENGTH_SHORT).show();



                                progressDialog.cancel();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SignUp.this,"Reg Faild", Toast.LENGTH_SHORT).show();

                                progressDialog.cancel();
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