package com.example.writeexternal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="important" ;
    EditText etst;
    Button bnwd, bnrd, bnclear;
    Context context;
    private final int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etst = findViewById(R.id.etsometext);
        bnwd = findViewById(R.id.bnwd);
        bnrd = findViewById(R.id.bnrd);
        bnclear =findViewById(R.id.clear);

        bnwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = etst.getText().toString();

                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                REQUEST_CODE);

                        return;
                    }

                    File mydrive = getExternalFilesDir("mydir");
                    File myfile = new File(mydrive, "textfile.txt");
                    if (mydrive.exists()) {
                        mydrive.mkdir();
                    } else {
                        myfile.delete();
                        myfile.createNewFile();
                    }

                    FileOutputStream four = new FileOutputStream(myfile);
                    four.write(message.getBytes());
                    four.close();

                    Toast.makeText(getBaseContext(), "File Created On SD card", Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        bnrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg;
                String buff = "";

                try {
                    File mydir = getExternalFilesDir("mydir");
                    File myFile = new File(mydir, "textfile.txt");

                    FileInputStream fs = new FileInputStream(myFile);

                    BufferedReader bfread = new BufferedReader(new InputStreamReader(fs));

                    while ((msg = bfread.readLine()) != null) {
                        buff+= msg;

                    }
                    etst.setText(buff);
                    bfread.close();
                    fs.close();
                    Toast.makeText(getBaseContext(), "Retrieved data from file on buffer ", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        bnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etst.setText("");
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v(TAG, "Permission : " + permissions[0] + "was " + grantResults[0]);
        }
    }
}