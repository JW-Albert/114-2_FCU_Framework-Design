package org.example;

public class Car {
    String color;
    String brand;

    public Car (String brand, String color) {
        this.brand = brand;
        this.color = color;
    }

    public void displayInfo () {
        System.out.println("This is a " + color + "'s " + brand);
    }
}
