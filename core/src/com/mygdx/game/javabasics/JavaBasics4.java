package com.mygdx.game.javabasics;

/**
 * @author y.gladkikh
 */
public class JavaBasics4 {

    public static void main(String[] args) {
        printWhetherNumberIsLowerOrHigher(100, 100);
        printWhetherNumberIsLowerOrHigher(89, 190);
    }

    static void printWhetherNumberIsLowerOrHigher(int number, int compareNumber) {
        if (number < compareNumber) {
            System.out.println("Number is lower than " + compareNumber);
        } else {
            System.out.println("Number is higher than " + compareNumber);
        }
    }
}
