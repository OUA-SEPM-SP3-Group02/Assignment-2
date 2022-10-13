package com.sepm.model;

public class ServiceDeskMember extends User{

    private final String serviceLevel;

    public ServiceDeskMember(String id, String name, String serviceLevel, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.serviceLevel = serviceLevel;
        this.password = password;
    }



    public String getServiceLevel(){
        return this.serviceLevel;
    }

}
