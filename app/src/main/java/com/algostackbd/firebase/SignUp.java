package com.algostackbd.firebase;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Random;

public class SignUp extends AppCompatActivity {

   EditText regName, regUsername, regEmail, regPhoneNo, regPassword;
    Button signbtn, tologin;
    ImageView imgup;
    Uri uri;
    Bitmap bitmap;

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
        imgup = findViewById(R.id.imgupload);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        progressDialog = new ProgressDialog(this);

        imgup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(SignUp.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start(101);

            }
        });


        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname = regName.getText().toString();
                String username = regUsername.getText().toString();
                String email = regEmail.getText().toString().trim();
                String phoneNumber =  regPhoneNo.getText().toString();
                String password = regPassword.getText().toString();

                progressDialog.show();

                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference uplode = storage.getReference("image"+new Random().nextInt(50));

                uplode.putFile(uri)
                                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        uplode.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {


                                                firebaseAuth.createUserWithEmailAndPassword(email,password)
                                                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                                            @Override
                                                            public void onSuccess(AuthResult authResult) {

                                                                firebaseFirestore.collection("Users").document(FirebaseAuth.getInstance().getUid())
                                                                        .set(new UserModel(fullname,username,phoneNumber,email,uri.toString()));

                                                                progressDialog.cancel();

                                                                Intent myintent = new Intent(SignUp.this, MainActivity.class);
                                                                startActivity(myintent);
                                                                finish();

                                                                Toast.makeText(SignUp.this, "Registation Succesfull", Toast.LENGTH_LONG).show();


                                                            }
                                                        });

                                            }
                                        });
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode ==101 && resultCode == Activity.RESULT_OK ){

            uri = data.getData();

            try {
                InputStream inputStream  = this.getContentResolver().openInputStream(uri);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imgup.setImageBitmap(bitmap);

            }catch (Exception e)
            {

            }



        }
    }

}