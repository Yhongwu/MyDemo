package com.howard.test.test02;

/**
 * 爬行动物
 * 组合
 */
public class Reptilia {

    private Animal animal;

    public Reptilia(Animal animal) {
        this.animal = animal;
    }

    public void crawling() {
        System.out.println("爬行...");
    }
    public void breathing() {
        animal.breathing();
    }


    public static void main(String[] args) {
        Animal animal = new Animal();
        Reptilia reptilia = new Reptilia(animal);
        reptilia.breathing();;
        reptilia.crawling();
    }
}
