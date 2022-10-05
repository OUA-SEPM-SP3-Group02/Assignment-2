package com.sepm.login;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

import static com.sepm.login.Login.login;
import static com.sepm.login.Register.registerUser;


public class Main {

        static Scanner sc = new Scanner(System.in);


        public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
            String choice;
            do {
                // Displays Menu
                displayMenu();
                choice = sc.nextLine();
                // process user's selection
                // note use of toUpperCase()
                switch (choice.toUpperCase()) {
                    case "A":
                        login();
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
