package com.sepm.login;

public class User {

        private int id;
        private String name;
        private int level;
        private String email;
        private String password;

        public User(int id, String name, int level, String email, String password){
            this.level = id;
            this.name = name;
            this.level = level;
            this.email = email;
            this.password = password;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getLevel() {
            return level;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }


