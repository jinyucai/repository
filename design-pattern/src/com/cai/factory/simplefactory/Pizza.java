package com.cai.factory.simplefactory;

public abstract class Pizza {

    protected String name;

    public abstract void prepare();

    public void beak(){
        System.out.println(name + " beaking");
    }

    public void cut(){
        System.out.println(name + " cutting");
    }

    public void box(){
        System.out.println(name + " boxing");
    }

    public void setName(String name) {
        this.name = name;
    }
}
