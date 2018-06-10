package com.howard.test.test02;

/**
 * 飞行动物
 * 继承
 */
public class FlyingAnimals extends Animal{
    public void filying() {
        System.out.println("飞行...");
    }

    public static void main(String[] args) {
        FlyingAnimals flyingAnimals = new FlyingAnimals();
        flyingAnimals.breathing();
        flyingAnimals.filying();
    }
}
