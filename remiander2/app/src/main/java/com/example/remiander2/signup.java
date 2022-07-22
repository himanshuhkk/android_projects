package com.example.remiander2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class signup extends AppCompatActivity {
    Button submit;
    EditText fname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        submit = findViewById(R.id.submit);
        fname = findViewById(R.id.fname);
    }
    public void submit(View view){

        Toast.makeText(this, "run run", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this , login.class);
        startActivity(intent);
    }
}