package com.sepm.login;

import java.util.Scanner;



public class Main {

        static Scanner sc = new Scanner(System.in);
        private static User user = new User();

        public static void main(String[] args) {
            String choice;
            do {
                // Displays Menu
                displayMenu();
                choice = sc.nextLine();
                // process user's selection
                // note use of toUpperCase()
                switch (choice.toUpperCase()) {
                    case "A":
                        //loginUser();
                        break;
                    case "B":
                        //forgotPassword();
                        break;
                    case "C":
                        registerUser();
                        break;
                    case "X":
                        System.out.println("Exiting the program...");
                        break;
                    default:
                        System.out.println("Error - invalid selection!");
                }

            } while (!choice.equalsIgnoreCase("X"));

        }

    public static void registerUser() {
        System.out.println("What's your email address?");
        String email = sc.next();
        System.out.println("What's your full name?");
        String name = sc.next();
        System.out.println("What's your phNumber?");
        String phNumber = sc.next();
        System.out.println("Please enter a password");
        String password = sc.next();
        User newUser = new User(email, name, phNumber, password);
        user.register(newUser);
        System.out.println("Successfully Registered!");



    }

        // Displays the Menu
        public static void displayMenu() {
            System.out.println("IT Ticketing");
            System.out.println("");
            System.out.println("A: Login");
            System.out.println("B: Forgot Password");
            System.out.println("C: Register");
            System.out.println("X: Exit program");
            System.out.println("");
            System.out.print("Please enter your selection: ");
        }
    }
