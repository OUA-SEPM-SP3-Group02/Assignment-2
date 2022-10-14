package com.sepm.view;

import com.sepm.core.Ascii;
import com.sepm.core.Request;
import com.sepm.core.Response;
import com.sepm.core.View;

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
}
