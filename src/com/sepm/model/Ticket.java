package com.sepm.model;

import java.util.ArrayList;

public class Ticket {
    private final String ticketId;
    private final String ticketTitle;
    private final String ticketDescription;
    private final String ticketIssuer;
    private final String ticketEmail;
    private final String ticketLevel;
    private final String ticketStatus;
    private final String assignedTo;

    //Private Static Global Array of tickets, is accessed via our getters
    private static Ticket[] tickets;

    public Ticket(String id, String title, String description, String issuer, String email, String level, String status, String assignedTo) {
        this.ticketId = id;
        this.ticketTitle = title;
        this.ticketDescription = description;
        this.ticketIssuer = issuer;
        this.ticketEmail = email;
        this.ticketLevel = level;
        this.ticketStatus = status;
        this.assignedTo = assignedTo;
    }

    public String toString() {
        String ticketSummary = "";
        ticketSummary += this.ticketId + ":";
        ticketSummary += this.ticketTitle + ":";
        ticketSummary += this.ticketDescription + ":";
        ticketSummary += this.ticketIssuer + ":";
        ticketSummary += this.ticketEmail + ":";
        ticketSummary += this.ticketLevel + ":";
        ticketSummary += this.ticketStatus;
        return ticketSummary;
    }

    //Accepts an array of ticket objects and binds the array to the static private tickets array
    public static void bindTicketData(Ticket[] tickets) {
        Ticket.tickets = tickets;
    }

    //----------------------- STATIC GETTERS -----------------------
    //These methods will provide a way to get ticket models from any
    //caller in the program.

    //**** GET ALL METHOD ****\\
    //Returns all the tickets
    public static Ticket[] getAll() {
        return Ticket.tickets;
    }

    //**** GET WERE ISSUER METHOD ****\\
    //Returns all the tickets that match that issuer
    public static Ticket[] getWereIssuer(String ticketIssuer) {
        //TODO ADD LOGIC
        return null;
    }

    //**** GET WERE LEVEL METHOD ****\\
    //Returns all the tickets that match a requested level
    public static Ticket[] getWereLevel(String level) {

        ArrayList<Ticket> output = new ArrayList<>();

        for (Ticket ticket : Ticket.tickets){
            if(ticket.getTicketLevel().equals(level)){
                output.add(ticket);
            }
        }

        return output.toArray(new Ticket[0]);
    }

    public static Ticket[] getWhereName(String name) {
        ArrayList<Ticket> output = new ArrayList<>();
        for (Ticket ticket:Ticket.tickets) {
            if (ticket.getAssignedTo().equals(name)) {
                output.add(ticket);
            }
        }
        return output.toArray(new Ticket[0]);
    }

    public static Ticket[] checkAssignmentStatus() {
        ArrayList<Ticket> analysedTickets = new ArrayList<>();
        for (Ticket tickets : Ticket.tickets) {
            if (Ticket.checkAssignmentStatus().equals("")) {

            }
        }
        return analysedTickets.toArray(new Ticket[0]);
    }

    //@Karsten please feel free to start to add more getters for different criteria!
    //try and make them generic in the sense that we can reuse them! - Jack Harris



    public String getTicketId() {
        return this.ticketId;
    }

    public String getTicketTitle() {
        return this.ticketTitle;
    }

    public String getTicketDescription() {
        return this.ticketDescription;
    }

    public String getTicketIssuer() {
        return this.ticketIssuer;
    }

    public String getTicketEmail() {
        return this.ticketEmail;
    }

    public String getTicketLevel() {
        return this.ticketLevel;
    }

    public String getTicketStatus() {
        return this.ticketStatus;
    }

    public String getAssignedTo() {
        return this.assignedTo;
    }
}

