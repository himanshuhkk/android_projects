package com.example.login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.verify);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);


    }
    public void verify(View view){
        if(username.getText().toString().equals("Himanshu") && password.getText().toString().equals("good_boy")){
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    Intent intent = new Intent(this , MainActivity2.class);
//                    startActivity(intent);
//                    Toast.makeText(MainActivity.this , "successfully login , Redirecting ..." , Toast.LENGTH_SHORT).show();
//                }
//            });

        Intent intent = new Intent(this , MainActivity2.class);
        Toast.makeText(MainActivity.this , "successfully login , Redirecting ..." , Toast.LENGTH_SHORT).show();
        startActivity(intent);

        }
        else{
            Toast.makeText(MainActivity.this , "Incorrect username or password" , Toast.LENGTH_SHORT).show();
        }
    }
}