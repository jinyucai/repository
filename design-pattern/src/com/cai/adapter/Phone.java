package com.cai.adapter;

public class Phone {

    public static void main(String[] args) {
        chargePhone();

    }


    public static void chargePhone(){
        Adapter adapter = new Adapter();
        adapter.charge5();
    }

    private AdapterObject adapterObject;

    public Phone(AdapterObject adapterObject){
        this.adapterObject = adapterObject;
    }

    public void charge(){
        adapterObject.charge5();
    }

}
