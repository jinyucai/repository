package com.cai.adapter;

public class Client {
    public static void main(String[] args) {
        Phone phone = new Phone(new AdapterObject(new Voltage220()));
        phone.charge();
    }
}
