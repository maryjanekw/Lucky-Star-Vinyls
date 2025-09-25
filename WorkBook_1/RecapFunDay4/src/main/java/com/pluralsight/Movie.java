package com.pluralsight;

public class Movie {
    public static void main(String[] args) {
        String movieTitle = "Tokyo Tribe";
        int friends = 6;
        double pizzaPrice = 2.99;
        boolean popcorn = true;

        System.out.println("We're watching " + movieTitle + " with " + friends + " friends.");
        System.out.println("Pizza cost $" + pizzaPrice + " each.");
        System.out.println("Popcorn? " + popcorn);
        System.out.println("Total cost of pizza: $" + (1 + friends) * 2.99);

        if (popcorn) {
            System.out.println("Popcorn time!");
        }else{
            System.out.println("No popcorn needed.");
        }
    }
}
