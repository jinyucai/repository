package com.cai.factory.methodFacory;

import com.cai.factory.simplefactory.Pizza;

public interface AbsFactory {

    public Pizza createPizza(String pizzaType);

}
