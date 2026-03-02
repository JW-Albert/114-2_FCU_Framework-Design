package org.example;

public class Car {
    String color;
    private String brand;
    Integer speed;

    public Car (String brand, String color, Integer speed) {
        this.brand = brand;
        this.color = color;
        this.speed = speed;
    }

    public String getBrand () {
        return this.brand;
    }

    public void setBrand(String newBrand) {
        this.brand = newBrand;
    }

    public void displayInfo () {
        System.out.println("This is a " + color + "'s " + brand + ". Speed: " + this.speed);
    }

    public void accelerate () {
        speed += 10;
    }
}
