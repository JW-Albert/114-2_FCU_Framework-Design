package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // 建立物件
        Car myCar = new Car("Toyota", "紅色");

        // 呼叫物件的方法
        myCar.displayInfo();

        ArrayList<Car> myCars= new ArrayList<>();
        myCars.add(new Car("BMW", "Black"));
        myCars.add(new Car("Benz", "White"));
        myCars.add(new Car("Totoya", "White"));

        // For-Loop
        System.out.println("\n");
        for (int i = 0 ;i < myCars.size() ;i++) {
            myCars.get(i).displayInfo();
        }

        // Ultra For-Loop
        System.out.println("\n");
        for (Car car: myCars) {
            car.displayInfo();
        }

        // Stream
        System.out.println("\n");
        myCars.forEach(car -> car.displayInfo());
    }
}
