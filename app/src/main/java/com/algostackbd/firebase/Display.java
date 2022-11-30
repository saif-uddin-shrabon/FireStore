package com.algostackbd.firebase;

import static android.text.TextUtils.isEmpty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Display extends AppCompatActivity {

    EditText name,dept,batch,id,sec;
    Button go;
    ProgressBar progressBar;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        name = findViewById(R.id.fname);
        dept = findViewById(R.id.depName);
        batch = findViewById(R.id.batch);
        id = findViewById(R.id.idNumber);
        sec = findViewById(R.id.section);
        go = findViewById(R.id.realBTN);
        progressBar = findViewById(R.id.prog);

        progressBar.setVisibility(View.INVISIBLE);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String Name = name.getText().toString();
                String Dept = dept.getText().toString();
                String ID = id.getText().toString();
                String Sec = sec.getText().toString();
                String Batch = batch.getText().toString();


                if (isEmpty(Name)){
                    name.setError("Name field cannot be empty");
                    name.requestFocus();
                }else if(isEmpty(Dept)){
                    dept.setError("Dept field cannot be empty");
                    dept.requestFocus();
                }else if(isEmpty(ID)){
                    id.setError("ID field cannot be empty");
                    id.requestFocus();
                }else if(isEmpty(Sec)){
                    sec.setError("Sec field cannot be empty");
                    sec.requestFocus();
                }else if(isEmpty(Batch)){
                    batch.setError("Batch field cannot be empty");
                    batch.requestFocus();
                }
                else {



                    firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference root = firebaseDatabase.getReference("User");
                    userHelper helper = new userHelper(Name,Dept,Batch,ID,Sec);
                    root.child(ID).setValue(helper);

                    progressBar.setVisibility(View.GONE);

                    Toast.makeText(Display.this,"Data inserted",Toast.LENGTH_LONG).show();

                    name.setText("");
                    dept.setText("");
                    batch.setText("");
                    id.setText("");
                    sec.setText("");


                }


            }
        });


    }
}