package com.mygdx.game.javabasics;

/**
 * @author y.gladkikh
 */
public class JavaBasics3 {

    public static void main(String[] args) {
        int a = 10;
        int b = 50;

        int c = action(a, b);

        int c2 = action(99, 9);

        int c3 = action(7, 6);
    }

    static int action(int a, int b) {
        return a + b * 10;
    }
}
