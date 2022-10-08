package com.sepm.model;

public class User {

    protected String id;
    protected String name;
    protected String email;
    protected String password;

    private static StaffMember[] staffMembers;
    private static ServiceDeskMember[] serviceDeskMembers;

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
}
