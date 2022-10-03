package com.sepm.view;

import com.sepm.controller.TicketManagerApp;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TicketManagerUI {
    private TicketManagerApp ticketManagerApp;
    private static Scanner stdin = new Scanner(System.in);

    public TicketManagerUI(TicketManagerApp ticketManagerApp) {
        this.ticketManagerApp = ticketManagerApp;

        try {
            this.ticketManagerApp.loadTickets("tickets.xml");
            System.out.println("Loading tickets...");

        } catch (IOException e) {
            System.out.println("File not found.");
            System.out.println();
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        } catch (SAXException e) {
            System.out.println(e.getMessage());
        }


        String ticketListing = this.ticketManagerApp.displayAllTickets();
        displayTickets(ticketListing);

    }

    private static void displayTickets(String ticketList) {
        String[] ticketListDetails = ticketList.split("\n");

        int i = 0;
        while (i<ticketListDetails.length) {
            String[] ticketDetails = ticketListDetails[i].split(":");

            String ticketId = ticketDetails[0];
            String title = ticketDetails[1];
            String description = ticketDetails[2];
            String issuedBy = ticketDetails[3];
            String email = ticketDetails[4];
            String level = ticketDetails[5];
            String status = ticketDetails[6];

            System.out.println("Ticket ID: " + ticketId);
            System.out.println("Title: " + title);
            System.out.println("Description: " + description);
            System.out.println("Issuer: " + issuedBy);
            System.out.println("Email: " + email);
            System.out.println("Severity: " + level);
            System.out.println("Status: " + status);
            System.out.println();

            i += 1;
        }

    }

    /*public void createTicket() {
        // Why these variables public?
        public int ticketId;
        public String title;
        public String description;
        public String issuer;
        public String email;
        public String level;
        public String status;
        System.out.println("Please provide details of the issue: ");
        description = sc.nextLine();


    }*/
}
