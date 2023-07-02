package com.mygdx.game.javabasics;

/**
 * @author y.gladkikh
 */
public class JavaBasics5 {

    public static void main(String[] args) {

        int age = 20;
        String name = "Yana";
        String surname = "Glad";

        int age2 = 52;
        String name2 = "Elon";
        String surname2 = "Musk";

        System.out.println("Person 1 with name " + name + " " + surname);
        System.out.println("Person 2 with name " + name2 + " " + surname2);

        Human elon = new Human("Elon", "Musk", 52);
    //  System.out.println("Person 2 with name " + elon.name + " " + elon.surname);
        elon.printNameAndSurname();
    }
}
