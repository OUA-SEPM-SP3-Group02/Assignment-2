package com.sepm.login;

import java.util.HashMap;

public class User {

    private String email;
    private String name;
    private String phNumber;
    private String password;

    private static final HashMap<String, String> loginUser = new HashMap<>();

    public User(){
        this.email= "";
        this.name="";
        this.phNumber="";
        this.password="";
    }

        public User(String email, String name, String phNumber, String password){
            this.email = email;
            this.name = name;
            this.phNumber = phNumber;
            this.password = password;
        }

        //access from other classes
        public HashMap<String, String> getUserMap(){
            return loginUser;
        }

        public String getEmail() {return this.email;}

        public String getName() {
            return this.name;
        }

        public String getPhNumber() {return this.phNumber; }

        public String getPassword() {
            return password;
        }

        public boolean doesUserExist (User user) {
            if (loginUser.containsKey(user.getEmail()) && loginUser.containsValue(user.getPassword())) {
                return true;
            } else {
                System.out.println("incorrect email and/or password");
            }
            return false;
        }

        public void register (User user){
            loginUser.put(user.getEmail(),user.getPassword());
        }

        public void login (User user) {
            if(doesUserExist(user)) {
                System.out.println("Hello " + user.getName());
            } else {
                System.out.println("No user with username " +user.getEmail());
            }
        }
    }



