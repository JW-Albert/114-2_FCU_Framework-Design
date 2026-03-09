package org.example;

import java.util.HashMap;

public class GenericForEachExample {
    public static void main(String[] args) {
        HashMap<String ,Integer> scores = new HashMap<>();
        scores.put("Albert" ,100);
        scores.put("Andy" ,100);

        int albertScore = scores.get("Albert");
        System.out.println("Albert's score: " + albertScore);

        for (String n: scores.keySet()) {
            System.out.printf("\nThe score of %s is %d." ,n ,scores.get(n));
        }
    }
}
