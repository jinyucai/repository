package com.cai.singleton.type6;

/**
 * 静态内部类
 */
public class SingleTon06 {
    public static void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1 == instance2);
    }
}

class Singleton{
    private Singleton(){

    }

    private static class SingletonInstance{
        private static Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance(){
        return SingletonInstance.INSTANCE;
    }
}
