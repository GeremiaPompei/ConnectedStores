package com.example;

public class MyDomain {
    private static MyDomain myDomain;

    public static MyDomain getInstance() {
        if (myDomain == null)
            myDomain = new MyDomain();
        return myDomain;
    }

    private MyDomain() {
        this.address = address;
    }

    private String address = "/localhost";

    public String getDomain() {
        return "https:/" + this.address + ":8080/";
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
