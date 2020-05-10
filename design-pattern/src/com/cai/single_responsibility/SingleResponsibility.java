package com.cai.single_responsibility;

/**
 * 单一职责原则，
 * 真正的单一原则是分割类的，加入业务足够简单可以分割方法
 */
public class SingleResponsibility {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        vehicle.reodRun("汽车");
        vehicle.waterRun("驱逐舰");
        vehicle.airRun("歼20");
    }
}

class Vehicle{

    public void reodRun(String vehicle){
        System.out.println(vehicle + "在路上跑");
    }

    public void waterRun(String vehicle){
        System.out.println(vehicle + "在水上航行");
    }

    public void airRun(String vehicle){
        System.out.println(vehicle + "在天上飞");
    }

}
