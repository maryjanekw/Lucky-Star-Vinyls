package com.pluralsight;

import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        //Primitive variables
        double weight = 144;
        char firstletter = 'M';
        boolean isHappy = true;


        // Reference variables (Strings are objects)
        String firstname = "Maryjane";
        String lastname = "Watanabe";
        int age = 26;
        String fullName = firstname + " " + lastname;
        String favoriteColor = "lavender";
        String favoriteArtist = "eve";
        boolean ateBreakfast = true;

        System.out.println(age);
        System.out.println(fullName);
        System.out.println(favoriteColor);
        System.out.println(favoriteArtist);
        System.out.println(ateBreakfast);

        boolean canYouDoSomethingAboutIt = false;

        // !isHappy = false
        // !canYouDoSomethingAboutIt = true

        // && == AND
        // & = combiningText, combineNumbers_
        if(isHappy && canYouDoSomethingAboutIt) {
            System.out.println("You're happy and can do something about it");
        } else if(isHappy & !canYouDoSomethingAboutIt) {
            System.out.println("You're happy, no action needed");
        } else if(!isHappy && canYouDoSomethingAboutIt){
            System.out.println("You're not happy, but you can change it");
        }

        if(isHappy && ateBreakfast){
            System.out.println("Nice job!");
        } else if (isHappy & !ateBreakfast) {
            System.out.println("You could be hungry");
        } else if (!isHappy & !ateBreakfast){
            System.out.println("Do something about it");
        }


    }

}