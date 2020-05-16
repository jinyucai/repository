package com.cai.builder;

public class CommomHouse extends HouseBuilder {


    @Override
    public void design() {
        house.setHign(10);
    }

    @Override
    public void buildBasic() {
        System.out.println(" 给普通" + house.getHign() + "米楼打地基");
    }

    @Override
    public void buildWall() {
        System.out.println(" 给普通" + house.getHign() + "楼打地基");
    }

    @Override
    public void buildEoof() {
        System.out.println(" 给普通" + house.getHign() + "楼打地基");
    }
}
