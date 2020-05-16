package com.cai.adapter;

public class LingChong extends Strengthen {

    public static void main(String[] args) {
        //想要重写那个方法自己定义
        Strengthen strengthen = new Strengthen() {
            @Override
            public void spear() {
                System.out.println("林冲的长枪得到了加强");
            }
        };
        strengthen.spear();

    }

}
