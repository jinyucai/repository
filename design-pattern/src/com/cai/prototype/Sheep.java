package com.cai.prototype;

import java.io.*;

/**
 * 这里序列化需要深克隆用的
 */
public class Sheep implements Serializable, Cloneable {

    private String name;
    private Integer age;
    private String color;
    private Sheep friend;
    private Dog dog;
    public Sheep(String name, Integer age, String color){
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Sheep getFriend() {
        return friend;
    }

    public void setFriend(Sheep friend) {
        this.friend = friend;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "Sheep{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                '}';
    }

    @Override
    protected Object clone() {
        Object deep = null;
        try {
            deep = super.clone();
            //单独处理属性对象
            Sheep sheep = (Sheep) deep;
            dog = (Dog) dog.clone();
            sheep.setDog(dog);
            return sheep;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    //方式二深克隆
    public Object deppClone(){
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;

        try {
            //读取整个对象
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            //反序列化读取
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            Sheep sheep = (Sheep) ois.readObject();
            return sheep;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                if(ois != null){
                    ois.close();
                }
                if(bis != null){
                    bis.close();
                }
                if(oos != null){
                    oos.close();
                }
                if(bos != null){
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
