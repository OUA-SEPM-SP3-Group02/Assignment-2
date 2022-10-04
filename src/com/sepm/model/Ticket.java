package com.sepm.model;

public class Ticket {
    private final String ticketId;
    private final String ticketTitle;
    private final String ticketDescription;
    private final String ticketIssuer;
    private final String ticketEmail;
    private final String ticketLevel;
    private final String ticketStatus;

    //Private Static Global Array of tickets, is accessed via our getters
    private static Ticket[] tickets;

    public Ticket(String id, String title, String description, String issuer, String email, String level, String status) {
        this.ticketId = id;
        this.ticketTitle = title;
        this.ticketDescription = description;
        this.ticketIssuer = issuer;
        this.ticketEmail = email;
        this.ticketLevel = level;
        this.ticketStatus = status;
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
    public static Ticket[] getWereLevel(String ticketlevel) {

        return Ticket.tickets;
    }

    //@Karsten please feel free to start to add more getters for different criteria!
    //try and make them generic in the sense that we can reuse them! - Jack Harris

}

