package com.mygdx.game.javabasics.lecture2;

/**
 * @author y.gladkikh
 */
public class Boss extends Person {

    Boss(String name, String surname, int age, String company) {
        super(name, surname, age, company);
    }

    void doWork() {
        super.doWork();
        System.out.println("I am boss");
    }

    void fireEmployee(Employee employee) {
        if (employee.company.equals(this.company)) {
            System.out.println("Boss fired employee " + employee);
        } else {
            System.out.println("You can't fire employee from the different company!");
        }
    }

    void increaseEmployeeSalary(Employee employee, int increase) {
        employee.salary += increase;
    }
}
