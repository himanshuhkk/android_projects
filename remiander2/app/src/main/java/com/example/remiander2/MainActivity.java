package com.example.remiander2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    Button login;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
    }
    //final MediaPlayer mp = MediaPlayer.create(this  , R.raw.btnsound);
    public void login(View view){
        Intent intent = new Intent(this , login.class);
        Toast.makeText(this, "Switching to login page ...", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
    public void signup(View view){
        Intent intent = new Intent(this , signup.class);
        Toast.makeText(this, "Switching to Signup page ...", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}