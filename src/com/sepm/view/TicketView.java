package com.sepm.view;

import com.sepm.core.Ascii;
import com.sepm.core.Request;
import com.sepm.core.Response;
import com.sepm.core.View;
import com.sepm.model.Ticket;

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
        System.out.println("Status: " + ticket.getTicketStatus());
        System.out.println("Resolution Status: " + ticket.getResolvedStatus() + "\n");

        if (response.contains("error")) {
            System.out.println(Ascii.RED + response.get("error") + Ascii.RESET + "\n");
        }

        System.out.println("Press 'X' to return or press 'A' to update the status (switches between closed & open\nor 'B' to toggle the resolved and unresolved status");
        request.add("input", this.getUserInput());

        return request;
    }

    public Request showTicketDateRange(Response response) {
        Request request = new Request();

        System.out.println("Ticket Date Range Report:\n");
        System.out.println("Total tickets: " + (Ticket.getOpenAndUnResolvedDateRangeCount() + Ticket.getClosedAndUnResolvedDateRangeCount() + Ticket.getOpenAndResolvedDateRangeCount() + Ticket.getClosedAndResolvedDateRangeCount()));
        System.out.println("Open & UnResolved tickets: " + Ticket.getOpenAndUnResolvedDateRangeCount());
        System.out.println("Open & Resolved tickets: " + Ticket.getOpenAndResolvedDateRangeCount());
        System.out.println("Closed & UnResolved tickets: " + Ticket.getClosedAndUnResolvedDateRangeCount());
        System.out.println("Closed & Resolved tickets: " + Ticket.getClosedAndResolvedDateRangeCount() + "\n");


        Ticket[] tickets = (Ticket[]) response.get("ticket");

        System.out.printf("%-5s %-50s %-50s %-20s %-20s %-10s %-10s %-25s %-15s %-15s\n", "ID", "Title", "Description", "Issuer", "Email", "Level", "Status", "Assigned", "Date Created", "Date Closed");

        int i = 0;
        while (i < tickets.length) {
            String[] explodedArray = tickets[i].toString().split(":");
            System.out.printf("%-5s %-50s %-50s %-20s %-20s %-10s %-10s %-25s %-15s %-15s\n",
                    explodedArray[0],
                    explodedArray[1].substring(0, Math.min(explodedArray[1].length(), 40)) + "...",
                    explodedArray[2].substring(0, Math.min(explodedArray[2].length(), 40)) + "...",
                    explodedArray[3],
                    explodedArray[4],
                    explodedArray[5],
                    explodedArray[6],
                    explodedArray[7],
                    explodedArray[8],
                    explodedArray[9]);

            //System.out.println(tickets[i].toString());
            i += 1;
        }


        System.out.println("Enter X to return to previous screen.");
        request.add("input", this.getUserInput());

        return request;
    }

    public Request showArchivedTicket(Response response) {
        Request request = new Request();

        Ticket ticket = (Ticket) response.get("ticket");

        System.out.println("Showing Archived Ticket :'" + ticket.getTicketTitle() + "'");

        System.out.println("Title: " + ticket.getTicketTitle());
        System.out.println("Description: " + ticket.getTicketDescription());
        System.out.println("Level: " + ticket.getTicketLevel());
        System.out.println("Status: " + ticket.getTicketStatus() + "\n");

        System.out.println("Enter X to return to previous screen.");
        request.add("input", this.getUserInput());

        return request;
    }
}
