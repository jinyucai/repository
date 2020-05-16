package com.cai.prototype;

public class DeepClone {
    public static void main(String[] args) {
        Sheep sheep = new Sheep("多利", 12, "白色");
        sheep.setDog(new Dog());
        Sheep sheep1 = (Sheep) sheep.clone();
        Sheep sheep2 = (Sheep) sheep.clone();
        Sheep sheep3 = (Sheep) sheep.clone();
        Sheep sheep4 = (Sheep) sheep.clone();
        Sheep sheep5 = (Sheep) sheep.deppClone(); //第二种方式
        System.out.println(sheep.toString() + "===" + sheep.hashCode() + "===" + sheep.getDog().hashCode());
        System.out.println(sheep1.toString() + "===" + sheep1.hashCode() + "===" + sheep1.getDog().hashCode());
        System.out.println(sheep2.toString() + "===" + sheep2.hashCode() + "===" + sheep2.getDog().hashCode());
        System.out.println(sheep3.toString() + "===" + sheep3.hashCode() + "===" + sheep3.getDog().hashCode());
        System.out.println(sheep4.toString() + "===" + sheep4.hashCode() + "===" + sheep4.getDog().hashCode());
        System.out.println(sheep5.toString() + "===" + sheep5.hashCode() + "===" + sheep5.getDog().hashCode());
    }
}
