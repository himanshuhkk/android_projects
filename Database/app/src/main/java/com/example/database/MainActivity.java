package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Context context;
    ListView listView;
    List<String> names;
    Button bndel , bnupd;
    List<Contact> contacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        listView = findViewById(R.id.listView);
        bndel = findViewById(R.id.bndel);
        bnupd = findViewById(R.id.bnupdate);
        names = new ArrayList<>();

        DatabaseHandler db = new DatabaseHandler(this);
         //Insert contacts
        int totcon = db.getContactsCount();
        if(totcon<=0){
            db.addContact(new Contact("AAAA" , "1234567890"));
            db.addContact(new Contact("BBBB" , "4234567845"));
            db.addContact(new Contact("CCCC" , "1234567845"));
            db.addContact(new Contact("DDDD" , "3434567675"));
        }
        //READ ALL CONTACTS
        contacts = db.getAllContacts();
        for(Contact con : contacts){
            names.add(new String(
                    con.getName()
            ));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this , android.R.layout.simple_list_item_1, names);
        listView.setAdapter(adapter);

        bndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(Contact con : contacts){
                   db.deleteContact(con);
                }
                finish();
                startActivity(getIntent());
            }
        });
        bnupd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cntr = 1;
                for(Contact con : contacts){
                    String nm = con.getName();
                    con.setName(nm + cntr);
                    cntr++;
                    db.updateContact(con);
                }
                finish();
                startActivity(getIntent());
            }
        });

    }
}