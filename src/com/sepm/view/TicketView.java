package com.sepm.view;

import com.sepm.core.Request;
import com.sepm.core.Response;
import com.sepm.core.View;

public class TicketView extends View {

    public Request addNewTicket(Response response) {
        Request request = new Request();
        System.out.println("Creating new ticket");
        if (response.contains("ticketTitle")) {
            System.out.println("Ticket Title set to: " + response.get("ticketTitle"));
        }
        if (response.contains("ticketDescription")) {
            System.out.println("Ticket description set to: " + response.get("ticketDescription"));
        }
        if (response.contains("ticketLevel")) {
            System.out.println("Ticket level set to: " + response.get("ticketLevel"));
        }
        System.out.println(response.get("heading"));


        if (response.contains("error")) {
            System.out.println(response.get("error"));
        }



        request.add("input", this.getUserInput());

        return request;

    }
}
