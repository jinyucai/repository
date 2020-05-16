package com.cai.singleton.type5;

/**
 * 双重检查
 */
public class SingleTon05 {
    public static void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1 == instance2);
    }
}

class Singleton{
    private Singleton(){

    }

    private static volatile Singleton instance;

    public static Singleton getInstance(){
        if(instance == null){
            synchronized (Singleton.class){
                if(instance == null){
                    instance = new Singleton();
                    return instance;
                }
            }
        }
        return instance;
    }
}
