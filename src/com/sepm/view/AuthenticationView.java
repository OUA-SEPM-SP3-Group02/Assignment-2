package com.sepm.view;

import com.sepm.core.Ascii;
import com.sepm.core.Request;
import com.sepm.core.Response;
import com.sepm.core.View;

public class AuthenticationView extends View {

    public Request welcome(Response response){
        Request request = new Request();

        System.out.println("------------------------------------");
        System.out.println("Service Desk Application (SEPM v0.2)");
        System.out.println("------------------------------------\n");

        if(response.contains("notification")){
            System.out.println(Ascii.GREEN+response.get("notification")+Ascii.RESET+"\n");
        }

        System.out.println("A: Login");
        System.out.println("B: Register");
        System.out.println("C: Forgot Password");
        System.out.println("X: Exit program\n");

        if(response.contains("error")){
            System.out.println(Ascii.RED+response.get("error")+Ascii.RESET+"\n");
        }

        System.out.print("Please enter your selection: ");
        request.add("input",this.getUserInput());

        return request;
    }

    public Request login(Response response){

        Request request = new Request();

        if(response.contains("error")){
            System.out.println(Ascii.RED+response.get("error")+Ascii.RESET+"\n");
        }

        System.out.println(response.get("header"));
        request.add("input",this.getUserInput());

        return request;
    }

    public Request register(Response response){

        Request request = new Request();

        /*System.out.println("------------------------------------");
        System.out.println("Service Desk Application (SEPM v0.1)");
        System.out.println("------------------------------------\n");*/
        System.out.println("Register:\n");

        if(response.contains("notification")){
            System.out.println(Ascii.GREEN+response.get("notification")+Ascii.RESET+"\n");
        }


        System.out.println(response.get("header")+" or enter 'X' to cancel and return");

        if(response.contains("error")){
            System.out.println(Ascii.RED+response.get("error")+Ascii.RESET+"\n");
        }

        request.add("input",this.getUserInput());

        return request;
    }

    public Request forgotPassword(Response response){

        Request request = new Request();

        /*System.out.println("------------------------------------");
        System.out.println("Service Desk Application (SEPM v0.1)");
        System.out.println("------------------------------------\n");*/
        System.out.println("Forgot Password:\n");

        if(response.contains("notification")){
            System.out.println(Ascii.GREEN+response.get("notification")+Ascii.RESET+"\n");
        }


        System.out.println(response.get("header")+" or enter 'X' to cancel and return");

        if(response.contains("error")){
            System.out.println(Ascii.RED+response.get("error")+Ascii.RESET+"\n");
        }

        request.add("input",this.getUserInput());

        return request;
    }

}
