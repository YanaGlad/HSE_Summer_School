package com.mygdx.game.javabasics;

/**
 * @author y.gladkikh
 */
public class JavaBasics {

    public static void main(String[] args) {

        // ТИПЫ ДАННЫХ

        byte b = 0;
        int i = 1;
        short s = 0;
        double d = 0.0;
        float f = 0.0f;
        boolean bool = false;

        int i2;
        i2 = 0;

        i = 20;
        i2 = 33;

        int result = i - i2;
        System.out.println(result);

        result = i + 12;
        System.out.println(result);

        result %= 3; // equal to result = result % 3;
        System.out.println(result);

        result *= 10; // equal to result = result * 10;
        System.out.println(result);

        result /= 10;
        System.out.println(result);

        i = 0;
        i = i + 1;
        i += 1;
        i++; // инкремент

        i = i - 1;
        i -= 1;
        i--; // декремент

        // СТРОКИ

        String str = "some string";
        System.out.println(str);

        str += " some more string";
        System.out.println(str);

        str = "Number is: " + i; // + "\n" add new line previously
        System.out.println(str);

        str += "\nNumber 2 is: " + i2;
        System.out.println(str);
    }
}
