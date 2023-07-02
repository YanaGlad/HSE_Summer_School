package com.mygdx.game.javabasics;

/**
 * @author y.gladkikh
 */
public class JavaBasics2 {

    public static void main(String[] args) {

        int[] array1 = {1, 2, 3, 10, 0};

        int[] array = new int[5];
        array[0] = 1;
        array[1] = 2;
        array[2] = 3;
        array[3] = 10;
        array[4] = 0;

        String[] strings = new String[100];
        String[] strings1 = {"aa", "bb", "???"};

        short size = 5;
        int[] array2 = new int[size];

        for (int i = 0; i < size; i++) {
            array2[i] = i + 1;
            System.out.print(array2[i] + " ");
        }

        int i = 0;
        while (i < size) {
            array2[i] = i + 1;
            System.out.print(array2[i] + " ");
            i++;
        }
    }
}
