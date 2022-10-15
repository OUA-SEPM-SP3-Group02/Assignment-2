package com.sepm.view;

import com.sepm.core.Ascii;
import com.sepm.core.Request;
import com.sepm.core.Response;
import com.sepm.core.View;
import com.sepm.model.Ticket;

public class TicketView extends View {

    public Request addNewTicket(Response response) {
        Request request = new Request();

        System.out.println("------------------------------------");
        System.out.println("Service Desk Application (SEPM v0.1)");
        System.out.println("------------------------------------");
        System.out.println("Creating New Ticket:\n");

        if (response.contains("ticketTitle")) {
            System.out.println(Ascii.GREEN+"Ticket Title set to: " + response.get("ticketTitle")+Ascii.RESET);
        }
        if (response.contains("ticketDescription")) {
            System.out.println(Ascii.GREEN+"Ticket description set to: " + response.get("ticketDescription")+Ascii.RESET);
        }
        if (response.contains("ticketLevel")) {
            System.out.println(Ascii.GREEN+"Ticket level set to: " + response.get("ticketLevel")+Ascii.RESET);
        }

        System.out.println(response.get("heading")+"\n");

        if (response.contains("error")) {
            System.out.println(Ascii.RED+response.get("error")+Ascii.RESET);
        }

        request.add("input", this.getUserInput());

        return request;

    }

    public Request showTicket(Response response){
        Request request = new Request();

        Ticket ticket = (Ticket) response.get("ticket");

        System.out.println("------------------------------------");
        System.out.println("Service Desk Application (SEPM v0.1)");
        System.out.println("------------------------------------");
        System.out.println("Open Ticket "+ticket.getTicketId()+":\n");

        System.out.println("Title: "+ticket.getTicketTitle());
        System.out.println("Description: "+ticket.getTicketDescription());
        System.out.println("Level: "+ticket.getTicketLevel());
        System.out.println("Status: "+ ticket.getTicketStatus()+"\n");

        if (response.contains("error")) {
            System.out.println(Ascii.RED + response.get("error") + Ascii.RESET + "\n");
        }

        System.out.println("Press 'X' to return or press 'A' to update the status (switches between closed & open)");
        request.add("input", this.getUserInput());

        return request;
    }
}
