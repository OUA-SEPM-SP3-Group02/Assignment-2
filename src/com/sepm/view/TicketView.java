package com.sepm.view;

import com.sepm.core.Ascii;
import com.sepm.core.Request;
import com.sepm.core.Response;
import com.sepm.core.View;
import com.sepm.model.Ticket;

import java.util.ArrayList;

public class TicketView extends View {

    public Request addNewTicket(Response response) {
        Request request = new Request();

        System.out.println("Creating New Ticket:\n");

        if (response.contains("ticketTitle")) {
            System.out.println(Ascii.GREEN + "Ticket Title set to: " + response.get("ticketTitle") + Ascii.RESET);
        }
        if (response.contains("ticketDescription")) {
            System.out.println(Ascii.GREEN + "Ticket description set to: " + response.get("ticketDescription") + Ascii.RESET);
        }
        if (response.contains("ticketLevel")) {
            System.out.println(Ascii.GREEN + "Ticket level set to: " + response.get("ticketLevel") + Ascii.RESET);
        }

        System.out.println(response.get("heading") + "\n");

        if (response.contains("error")) {
            System.out.println(Ascii.RED + response.get("error") + Ascii.RESET);
        }

        request.add("input", this.getUserInput());

        return request;

    }

    public Request showTicket(Response response) {
        Request request = new Request();

        Ticket ticket = (Ticket) response.get("ticket");

        System.out.println("Open Ticket " + ticket.getTicketId() + ":\n");

        if (response.contains("notification")) {
            System.out.println(Ascii.GREEN + response.get("notification") + Ascii.RESET + "\n");
        }

        System.out.println("Title: " + ticket.getTicketTitle());
        System.out.println("Description: " + ticket.getTicketDescription());
        System.out.println("Level: " + ticket.getTicketLevel());
        System.out.println("Status: " + ticket.getTicketStatus() + "\n");

        if (response.contains("error")) {
            System.out.println(Ascii.RED + response.get("error") + Ascii.RESET + "\n");
        }

        System.out.println("Press 'X' to return or press 'A' to update the status (switches between closed & open)");
        request.add("input", this.getUserInput());

        return request;
    }

    public Request showTicketDateRange(Response response) {
        Request request = new Request();

        System.out.println("Ticket Date Range Report:\n");
        System.out.println("Open tickets: "+Ticket.getOpenDateRangeCount());
        System.out.println("Closed tickets: "+Ticket.getClosedDateRangeCount()+"\n");


        Ticket[] tickets = (Ticket[]) response.get("ticket");

         int i =0;
         while (i<tickets.length) {
             System.out.println(tickets[i].toString());
             i += 1;
         }


        System.out.println("Enter X to return to previous screen.");
        request.add("input", this.getUserInput());

        return request;
    }
}
