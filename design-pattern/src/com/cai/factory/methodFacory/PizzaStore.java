package com.cai.factory.methodFacory;

import java.lang.invoke.CallSite;
import java.util.Calendar;

public class PizzaStore {
    public static void main(String[] args) {
//        new BJPizzaFactory();
        new OrderPizza2(new BJFactory());
    }
}
