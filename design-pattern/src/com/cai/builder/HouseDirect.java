package com.cai.builder;

public class HouseDirect {

    private HouseBuilder houseBuilder;

    public HouseDirect(HouseBuilder houseBuilder){
        this.houseBuilder = houseBuilder;
    }

    public House build(){
        houseBuilder.design();
        houseBuilder.buildBasic();;
        houseBuilder.buildWall();
        houseBuilder.buildEoof();
        House house = houseBuilder.buildHouse();
        return house;
    }

}
