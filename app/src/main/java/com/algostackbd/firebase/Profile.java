package com.algostackbd.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.common.base.MoreObjects;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Profile extends AppCompatActivity {

    TextView fullName,userName,number,email,messengar;
    Button fetchBTN;

    FirebaseFirestore dbroot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fullName = findViewById(R.id.fullName);
        userName = findViewById(R.id.userName);
        number = findViewById(R.id.phoneNo);
        email = findViewById(R.id.EmailF);
        fetchBTN = findViewById(R.id.fetch);
        messengar = findViewById(R.id.chat);

        dbroot = FirebaseFirestore.getInstance();

        fetchBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DocumentReference document = dbroot.collection("Users").document("POBt4tGTMddtJ3OdoFM4veCUnZI2");

                document.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        if (documentSnapshot.exists()){

                            fullName.setText(documentSnapshot.getString("fullname"));
                            userName.setText(documentSnapshot.getString("username"));
                            number.setText(documentSnapshot.getString("phoneNumber"));
                            email.setText(documentSnapshot.getString("email"));

                            Toast.makeText(Profile.this,"Fetch Successfully", Toast.LENGTH_LONG).show();

                        }else
                        {
                            Toast.makeText(Profile.this,"Row not found", Toast.LENGTH_LONG).show();
                        }



                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Profile.this,"Failed to Fetch data", Toast.LENGTH_LONG).show();
                    }
                });


            }
        });

        messengar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, testPDF.class);
                startActivity(intent);
                finish();
            }
        });



    }
}