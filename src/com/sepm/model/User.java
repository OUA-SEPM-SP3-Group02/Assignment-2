package com.sepm.model;

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

    //-------------------- STAFF MEMBERS (END USERS) --------------------\\

    public static void bindStaffMemberUserData(StaffMember[] staffMembers){
        User.staffMembers = staffMembers;
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
}
