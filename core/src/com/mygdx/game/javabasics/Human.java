package com.mygdx.game.javabasics;

/**
 * @author y.gladkikh
 */
public class Human {
    int age;
    String name;
    String surname;

    Human(String name, String surname, int age) {
        this.age = age;
        this.name = name;
        this.surname = surname;
    }

    void printNameAndSurname() {
        System.out.println("Person with name: " + name + " " + surname);
    }
}
