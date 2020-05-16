package com.cai.prototype;

public class Client {

    public static void main(String[] args) {
        Sheep sheep = new Sheep("多利", 12, "白色");
        Sheep sheep1 = (Sheep) sheep.clone();
        Sheep sheep2 = (Sheep) sheep.clone();
        Sheep sheep3 = (Sheep) sheep.clone();
        Sheep sheep4 = (Sheep) sheep.clone();
        System.out.println(sheep.toString() + sheep.getFriend().hashCode());
        System.out.println(sheep1.toString() + sheep1.getFriend().hashCode());
        System.out.println(sheep2.toString() + sheep2.getFriend().hashCode());
        System.out.println(sheep3.toString() + sheep3.getFriend().hashCode());
        System.out.println(sheep4.toString() + sheep4.getFriend().hashCode());
    }
}
