package com.mygdx.game.javabasics.lecture2;

/**
 * @author y.gladkikh
 */
public class JavaBasics7 {

    public static void main(String[] args) {

        Employee employee = new Employee("Bob", "Bobov", 30, "Google", 17000);
        Employee employee2 = new Employee("Andrey", "Andreev", 35, "Amazon", 12000);
        Boss boss = new Boss("Oleg", "Olegov", 12, "Google");


        employee2.doWork();
        boss.fireEmployee(employee2);

        String str = null;
        Employee employee3 = null;

        Object object = (Object) employee;

        Lessons algebra = Lessons.ALGEBRA;

        switch (algebra) {
            case ALGEBRA:
                System.out.println("Algebra");
                break;
            case HISTORY:
                System.out.println(algebra.name());
                break;
            case GEOMETRY:
                System.out.println("Geometry");
                break;
        }

        int a = 1000;
        long s = 900000L;
        short d = (short) s;

        Computable[] computables = new Computable[3];
        computables[0] = new SumComputable();
        computables[1] = new MinusComputable();
        computables[2] = new SumComputable();
    }
}
