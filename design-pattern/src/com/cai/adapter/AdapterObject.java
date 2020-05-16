package com.cai.adapter;

public class AdapterObject implements IVoltage5 {

    private Voltage220 voltage220;

    public AdapterObject(Voltage220 voltage220){
        this.voltage220 = voltage220;
    }


    @Override
    public void charge5() {
        //变压
        int charge = voltage220.charge();
        int src = charge/44;
        System.out.println("五伏充电");
    }
}
