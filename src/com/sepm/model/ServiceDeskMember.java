package com.sepm.model;

public class ServiceDeskMember {
    private final String id;
    private final String name;
    private final String email;
    private final String serviceLevel;
    private final String password;
    private static ServiceDeskMember[] serviceDeskMembers;

    public ServiceDeskMember(String id, String name, String serviceLevel, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.serviceLevel = serviceLevel;
        this.password = password;
    }

    //Accepts an array of ticket objects and binds the array to the static private tickets array
    public static void bindServiceDeskData(ServiceDeskMember[] serviceDeskMembers) {
        ServiceDeskMember.serviceDeskMembers = serviceDeskMembers;
    }

    //----------------------- STATIC GETTERS -----------------------
    //These methods will provide a way to get ticket models from any
    //caller in the program.

    //**** GET ALL METHOD ****\\
    //Returns all the tickets
    public static ServiceDeskMember[] getServiceDeskMembers() {
        return ServiceDeskMember.serviceDeskMembers;
    }

    //**** GET WERE ISSUER METHOD ****\\
    //Returns all the tickets that match that issuer
    public static ServiceDeskMember[] getWereName(String name) {
        //TODO ADD LOGIC
        return null;
    }

    public String getPassword(){
        return this.password;
    }

    public String getName(){
        return this.name;
    }

    public String toString() {
        String serviceMembersSummary = "";
        serviceMembersSummary += this.id + ":";
        serviceMembersSummary += this.name + ":";
        serviceMembersSummary += this.email + ":";
        serviceMembersSummary += this.serviceLevel + ":";
        serviceMembersSummary += this.password + ":";
        return serviceMembersSummary;
    }

    public static ServiceDeskMember getServiceDeskMemberByEmail(String email){

        ServiceDeskMember outcome = null;

        for(ServiceDeskMember serviceDeskMember : ServiceDeskMember.serviceDeskMembers){
            if(serviceDeskMember.email.equals(email)){
                outcome =  serviceDeskMember;
            }
        }

        return outcome;
    }



    //@Karsten please feel free to start to add more getters for different criteria!
    //try and make them generic in the sense that we can reuse them! - Jack Harris

}
