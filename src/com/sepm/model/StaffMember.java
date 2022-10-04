package com.sepm.model;

public class StaffMember {
    private String name;
    private String email;
    private String password;
    private static StaffMember[] staffMembers;

    public StaffMember (String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    //Accepts an array of ticket objects and binds the array to the static private tickets array
    public static void bindStaffData(StaffMember[] staffMembers){
        StaffMember.staffMembers = staffMembers;
    }

    //----------------------- STATIC GETTERS -----------------------
    //These methods will provide a way to get ticket models from any
    //caller in the program.

    //**** GET ALL METHOD ****\\
    //Returns all the tickets
    public static StaffMember[] getStaffMembers(){
        return StaffMember.staffMembers;
    }

    //**** GET WERE ISSUER METHOD ****\\
    //Returns all the tickets that match that issuer
    public static StaffMember[] getWereName(String name ){
        //TODO ADD LOGIC
        return  null;
    }

    //@Karsten please feel free to start to add more getters for different criteria!
    //try and make them generic in the sense that we can reuse them! - Jack Harris

}
