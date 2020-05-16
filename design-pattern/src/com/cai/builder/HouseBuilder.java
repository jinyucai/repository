package com.cai.builder;

public abstract class HouseBuilder {
    protected House house = new House();

    public abstract void design();

    public abstract void buildBasic();

    public abstract void buildWall();

    public  abstract void buildEoof();

    public House buildHouse(){
        return house;
    }

}
