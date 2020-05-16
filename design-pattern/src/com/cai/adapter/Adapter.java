package com.cai.adapter;

public class Adapter extends Voltage220 implements IVoltage5 {

    @Override
    public void charge5() {
        int v = charge();
        int v5 = v/44;
        System.out.println("充电"+v5+"伏");
    }
}
