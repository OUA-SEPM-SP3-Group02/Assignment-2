package com.sepm.login;

import java.util.Scanner;

import static com.sepm.login.Main.displayMenu;


public class Register {

    private static final Scanner sc = new Scanner(System.in);
    private static User user = new User();


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


}
