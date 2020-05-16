package com.cai.singleton.type7;

import java.util.Calendar;

public class SingleTon07 {
    public static void main(String[] args) {
        Singleton instance1 = Singleton.INSTANCE;
        Singleton instance2 = Singleton.INSTANCE;
        System.out.println(instance1 == instance2);
    }
}

enum Singleton{
    INSTANCE;

    public void say(){
        System.out.println("ok");
    }
}