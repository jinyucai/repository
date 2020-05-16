package com.cai.decorator;

public class CoffeStore {
    public static void main(String[] args) {
        Coffe coffe = new NatieKoffe(new Sugger());
        coffe.cost();
    }

}
