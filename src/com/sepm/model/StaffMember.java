package com.sepm.model;

public class StaffMember extends User {
    private final String phone;

    public StaffMember(String id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
}
