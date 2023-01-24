package com.algostackbd.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Documented;
import java.util.Random;

public class testPDF extends AppCompatActivity {

    EditText t1,t2,t3,t4,t5;
    Button btnpdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_pdf);

        t1 = findViewById(R.id.pdffull_name);
        t2 = findViewById(R.id.pdfmail);
        t3 = findViewById(R.id.pdfnumberI);
        t4 = findViewById(R.id.pdfpass);
        t5 = findViewById(R.id.pdfuserNameI);
        btnpdf = findViewById(R.id.pdfBbtn);

        btnpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String   fullName = t1.getText().toString();
              String  UserName = t5.getText().toString();
              String  mail = t2.getText().toString();
              String  number =  t3.getText().toString();
              String pas = t4.getText().toString();


                try {
                    createPDF(fullName,UserName,mail,number,pas);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }




            }
        });
    }

    private void createPDF(String fullName,String UserName,String mail,String number,String pas) throws FileNotFoundException {
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();

        // create a string of all characters
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // specify length of random string
        int length = 7;

        for(int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphabet.length());

            // get character specified by index
            // from the string
            char randomChar = alphabet.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }

        String randomString = sb.toString();

        File file =  new File(path, randomString+".pdf");
        OutputStream outputStream = new FileOutputStream(file);

        PdfWriter writer = new PdfWriter(String.valueOf(file));
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        Paragraph fullname = new Paragraph("Full Name : "+fullName);
        Paragraph username = new Paragraph("User Name : "+UserName);
        Paragraph Mail = new Paragraph("Email  : "+mail);
        Paragraph Number = new Paragraph("Mobile Number : "+number);
        Paragraph Pass = new Paragraph("Password : "+pas);



        document.add(fullname);
        document.add(username);
        document.add(Mail);
        document.add(Number);
        document.add(Pass);

        document.close();


        Toast.makeText(testPDF.this, "PDF Create Sucsessfully", Toast.LENGTH_LONG).show();

    }
}