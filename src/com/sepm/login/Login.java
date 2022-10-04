package com.sepm.login;

import java.util.HashMap;
import java.util.Scanner;

public class Login {

    private static final Scanner sc = new Scanner(System.in);



    public static void login(){
        System.out.println("Email: ");
        String email = sc.next();
        System.out.println("Password: ");
        String password = sc.next();

        User x = new User();
        HashMap<String, String> loginuser = x.getUserMap(); // access hashmap

        find(loginuser, email, password);
    }

    public static void find(HashMap<String, String> loginUser, String email, String password) {
        for (String i : loginUser.keySet()) {
            if (i.equals(email) && loginUser.get(i).equals(password)) {
                System.out.println("User is Found!");
                System.out.println("Program will now terminate");
                System.exit(0);
                break;
            }
        }
        System.out.println("User Not Found. Login Again");
        login();
    }

}


