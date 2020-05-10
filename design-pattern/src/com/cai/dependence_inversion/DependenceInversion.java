package com.cai.dependence_inversion;

import javax.sound.midi.Soundbank;

/**
 * 依赖倒转原则
 */
public class DependenceInversion {
    public static void main(String[] args) {
        Person person = new Person();
        Wechat wechat = new Wechat();
        person.getInfo(new Email());
        person.getInfo(wechat);
    }
}

interface Ireceiver{
    public String getInfo();
}

class Email implements Ireceiver {

    @Override
    public String getInfo() {
        return "接收到了邮件信息";
    }
}

class Wechat implements Ireceiver {

    @Override
    public String getInfo() {
        return "接收到了微信信息";
    }
}

class Person {

    public void getInfo(Ireceiver ireceiver){
        String info = ireceiver.getInfo();
        System.out.println(info);
    }
}
