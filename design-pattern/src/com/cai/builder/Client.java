package com.cai.builder;

public class Client {
    public static void main(String[] args) {
        HouseDirect houseDirect = new HouseDirect(new CommomHouse());
        House house = houseDirect.build();
    }
}
