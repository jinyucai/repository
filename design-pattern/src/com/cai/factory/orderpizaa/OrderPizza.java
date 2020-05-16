package com.cai.factory.orderpizaa;

import com.cai.factory.simplefactory.CheesePizza;
import com.cai.factory.simplefactory.GreekPizza;
import com.cai.factory.simplefactory.Pizza;

import java.util.Scanner;

public class OrderPizza {

//    public OrderPizza() {
//        Pizza pizza = null;
//        do {
//            String pizzaType = choiceType();
//            if(pizzaType.equals("greek")){
//                pizza = new GreekPizza();
//                pizza.setName("希腊披萨");
//            }else if(pizzaType.equals("cheese")){
//                pizza = new CheesePizza();
//                pizza.setName("奶酪披萨");
//            }else {
//                break;
//            }
//            pizza.prepare();
//            pizza.beak();
//            pizza.cut();
//            pizza.beak();
//        }while (true);
//    }

    private SimplePizzaFactory simplePizzaFactory;
    Pizza pizza = null;
    public OrderPizza(SimplePizzaFactory simplePizzaFactory){
        createSimplePizzaFactoy(simplePizzaFactory);
    }

    private void createSimplePizzaFactoy(SimplePizzaFactory simplePizzaFactory){
        this.simplePizzaFactory = simplePizzaFactory;
        do {
            pizza = this.simplePizzaFactory.createPizza(choiceType());
            if(pizza != null){
                System.out.println("使用简单工厂模式创建pizza");
                pizza.beak();
                pizza.cut();
                pizza.beak();
            }else{
                break;
            }
        }while (true);

    }


    private String choiceType(){
        System.out.println("choice pizza type");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        return next;
    }
}
