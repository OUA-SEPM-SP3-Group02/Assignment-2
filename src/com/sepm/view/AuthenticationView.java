package com.sepm.view;

import com.sepm.core.Request;
import com.sepm.core.Response;
import com.sepm.core.View;

public class AuthenticationView extends View {

    public Request welcome(Response response){
        Request request = new Request();

        System.out.println("IT Ticketing");
        System.out.println("");
        System.out.println("A: Login");
        System.out.println("B: Forgot Password");
        System.out.println("C: Register");
        System.out.println("X: Exit program");
        System.out.println("");
        System.out.print("Please enter your selection: ");

        request.add("input",this.getUserInput());

        return request;
    }

}
