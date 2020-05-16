package com.cai.brige;

public class Client {

    public static void main(String[] args) {
        Phone qumian = new QuMian(new XiaoMi());
        qumian.doSomething();

        Phone liuhai = new LiuHai(new Vivo());
        liuhai.doSomething();
    }
}
