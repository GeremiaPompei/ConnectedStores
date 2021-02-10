package com.example.service;

import com.example.model.RecEntity;

public class RecBuilder {
    private static RecBuilder recBuilder;

    public static RecBuilder getInstance() {
        if (recBuilder == null)
            recBuilder = new RecBuilder();
        return recBuilder;
    }

    private RecBuilder() {
        reset();
    }

    private RecEntity rec;

    public String getAddressReceiver() {
        return addressReceiver;
    }

    public void setAddressReceiver(String addressReceiver) {
        this.addressReceiver = addressReceiver;
    }

    private String addressReceiver;

    public void reset() {
        rec = new RecEntity();
    }

    public RecEntity getRec() {
        return rec;
    }
}
