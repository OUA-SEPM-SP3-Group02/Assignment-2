package com.sepm.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {

    protected String id;
    protected String name;
    protected String email;
    protected String password;
    protected String phone;

    private static StaffMember[] staffMembers;
    private static ServiceDeskMember[] serviceDeskMembers;

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getPassword() {
        return this.password;
    }

    public static ServiceDeskMember[] getServiceDeskMembers() {
        return serviceDeskMembers;
    }

    public static StaffMember[] getStaffMembers(){
        return staffMembers;
    }

    //-------------------- STAFF MEMBERS (END USERS) --------------------\\

    public static void bindStaffMemberUserData(){
        User.staffMembers = new StaffMember[0];
    }

    //-------------------- SERVICE DESK MEMBERS --------------------\\

    public static void bindServiceDeskUserData(ServiceDeskMember[] serviceDeskMembers) {
       User.serviceDeskMembers = serviceDeskMembers;
    }

    public static ServiceDeskMember getServiceDeskMemberByEmail(String email){

        ServiceDeskMember outcome = null;

        for(ServiceDeskMember serviceDeskMember : User.serviceDeskMembers){
            if(serviceDeskMember.email.equals(email)){
                outcome =  serviceDeskMember;
            }
        }

        return outcome;
    }

    public static StaffMember getStaffMemberByEmail(String email){

        StaffMember outcome = null;

        for(StaffMember staffMember : User.staffMembers){
            if(staffMember.email.equals(email)){
                outcome =  staffMember;
            }
        }

        return outcome;
    }

    public static StaffMember[] getAllStaffMembers(){
        return User.staffMembers;
    }

    public static void addStaffMember(StaffMember staffMember){
        ArrayList<StaffMember> newStaffMembers = new ArrayList<>(Arrays.asList(User.staffMembers));

        newStaffMembers.add(staffMember);

        User.staffMembers = newStaffMembers.toArray(new StaffMember[0]);
    }

    public boolean validateTicketSelectionForUser(int id){
        boolean outcome = false;
        for(Ticket ticket : Ticket.getWhereName(this.getName())){
            if(ticket.getID() == id){
                outcome = true;
            }
        }
        return outcome;
    }
}
