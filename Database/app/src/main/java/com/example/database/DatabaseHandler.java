package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME= "contactsdbs";
    private static final String TABLE_CONTACTS = "contacts";
    private static final String Idno = "id";
    private static final String Names = "name";
    private static final String phno = "phone_number";

    public DatabaseHandler(Context context){
        super(context,DATABASE_NAME , null , DATABASE_VERSION);
    }
    //creating table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "(" +Idno+ " INTEGER PRIMARY KEY," + Names + " Text," + phno + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
//upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Dropping tables if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        //creating tables again
        onCreate(db);
    }
    //code to add new contact
    void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //content values to read the data and store
        values.put(Names , contact.getName());  //contact name
        values.put(phno , contact.getPhoneNumber()); //contact phone
        //inserting row
        db.insert(TABLE_CONTACTS,null,values);
        db.close(); //closing the database
    }
    //code to get a single contact
    Contact getContact(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        //cursor class is used to read data iteratively from table
        //Idno+"=?" used as 'where' in sql
        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{Idno,Names,phno} , Idno+"=?"
        , new String[]{String.valueOf(id)} , null , null , null , null );
        if(cursor !=null)
            cursor.moveToFirst();
        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1) , cursor.getString(2));
        return contact;
    }
    //code to get all contacts in the list view
    public List<Contact> getAllContacts(){
        List<Contact> contactList=new ArrayList<Contact>(); //arraylist to store data
    //select all query
        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery , null);
        //looping through all rows and adding to list
        if(cursor.moveToFirst()){
            do{
                Contact contact = new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                //adding contact to the list
                contactList.add(contact);
            }while (cursor.moveToNext());
    }
        //return contact list
        return contactList;
    }
    //code to update a single contact
    public int updateContact(Contact contact){
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(Names , contact.getName());
    values.put(phno , contact.getPhoneNumber());
    //updating row
    return db.update(TABLE_CONTACTS, values , Idno + "= ?" , new String[]{ String.valueOf(contact.getID())});
}
//delete single contact
    public void deleteContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS,  Idno+ "= ?" , new String[]{String.valueOf(contact.getID())});
        db.close();
    }
    //GETTING CONTACTS COUNT
    public  int getContactsCount(){
        String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);
        int totrec = cursor.getCount();
        cursor.close();
        //return count
        return  totrec;
    }

}
