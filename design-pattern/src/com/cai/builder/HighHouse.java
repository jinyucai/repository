package com.cai.builder;

public class HighHouse extends HouseBuilder {
    @Override
    public void design() {
        house.setColor("红色");
    }

    @Override
    public void buildBasic() {
        System.out.println(" 给"+house.getColor()+"高楼打地基");
    }

    @Override
    public void buildWall() {
        System.out.println("给"+house.getColor()+"高楼建墙");
    }

    @Override
    public void buildEoof() {
        System.out.println("给"+house.getColor()+"高楼建房顶");
    }
}
