package com.example.database;

public class Contact {
    int idn;
    String names;
    String phnos;

    public Contact() { }
    public Contact(int id, String name, String phnos) {
        this.idn=id;
        this.names=name;
        this.phnos=phnos;
    }

    public Contact(String name , String phnos) {
        this.names=name;
        this.phnos=phnos;
    }

    public String getName() {
        return this.names;
    }

    public String getPhoneNumber() {
        return this.phnos;
    }

    public void setID(int id) {
        this.idn=id;
    }

    public void setName(String name) {
        this.names=name;
    }

    public void setPhoneNumber(String phone_Number) {
        this.phnos = phone_Number;
    }

    public int getID() {
        return this.idn;
    }

}
