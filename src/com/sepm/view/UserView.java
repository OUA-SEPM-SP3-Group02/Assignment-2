package com.sepm.view;

import com.sepm.core.Request;
import com.sepm.core.Response;
import com.sepm.core.View;
import com.sepm.model.Ticket;

import java.util.ArrayList;

public class UserView extends View {

    public Request showTickets(Response response) {
        //SHOW HEADING
        System.out.println("Show tickets.");

        //create our new request
        Request request = new Request();

        //add our user input into the request
        request.add("input", this.getUserInput());

        //check if the response contains tickets
        if(response.contains("tickets")) {

            //if so then for each ticket output them
            for (Ticket ticket : (Ticket[]) response.get("tickets")) {
                System.out.println(ticket.toString());
            }
        }

        //check if the response contains an error
        if (response.contains("error")) {
            //if so output it
            System.out.println(response.get("error"));
        }
        return request;
    }
}
