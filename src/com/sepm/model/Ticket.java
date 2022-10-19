package com.sepm.model;

import com.sepm.service.XMLWriterService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Ticket {
    private final String ticketId;
    private final String ticketTitle;
    private final String ticketDescription;
    private final String ticketIssuer;
    private final String ticketEmail;
    private String ticketLevel;
    private String ticketStatus;
    private String assignedTo;
    private String dateCreated;
    private String dateClosed;

    //Private Static Global Array of tickets, is accessed via our getters
    private static Ticket[] tickets;

    public Ticket(String id, String title, String description, String issuer, String email, String level, String status, String assignedTo, String dateCreated, String dateClosed) {
        this.ticketId = id;
        this.ticketTitle = title;
        this.ticketDescription = description;
        this.ticketIssuer = issuer;
        this.ticketEmail = email;
        this.ticketLevel = level;
        this.ticketStatus = status;
        this.assignedTo = assignedTo;
        this.dateCreated = dateCreated;
        this.dateClosed = dateClosed;
    }

    public void setAssignedTo(String name){
        this.assignedTo = name;
    }

    public String toString() {
        String ticketSummary = "";
        ticketSummary += this.ticketId + ":";
        ticketSummary += this.ticketTitle + ":";
        ticketSummary += this.ticketDescription + ":";
        ticketSummary += this.ticketIssuer + ":";
        ticketSummary += this.ticketEmail + ":";
        ticketSummary += this.ticketLevel + ":";
        ticketSummary += this.ticketStatus + ":";
        ticketSummary += this.assignedTo + ":";
        ticketSummary += this.dateCreated + ":";
        ticketSummary += this.dateClosed + ":";
        return ticketSummary;
    }

    //Add a new ticket
    public static void add(Ticket ticket){
        ArrayList<Ticket> newTickets = new ArrayList<>(Arrays.asList(Ticket.tickets));

        newTickets.add(ticket);

        Ticket.tickets = newTickets.toArray(new Ticket[0]);
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

        for (Ticket ticket : Ticket.tickets) {
            if (ticket.getTicketLevel().equals(level)) {
                output.add(ticket);
            }
        }

        return output.toArray(new Ticket[0]);
    }

    public static Ticket[] getWhereName(String name) {
        ArrayList<Ticket> output = new ArrayList<>();
        for (Ticket ticket : Ticket.tickets) {
            if (ticket.getAssignedTo().equals(name)) {
                output.add(ticket);
            }
        }
        return output.toArray(new Ticket[0]);
    }

    public static Ticket[] setStaffTicketsForUser(StaffMember name) {
    ArrayList<Ticket> staffTickets = new ArrayList<>();
    for (Ticket ticket:Ticket.tickets) {
        if (ticket.ticketIssuer.equals(name)) {
            staffTickets.add(ticket);
        }
    }
    return staffTickets.toArray(new Ticket[0]);
    }

    public static Ticket getWhereID(int id){
        Ticket outcome = null;
        for (Ticket ticket: Ticket.getAll()){
            if(ticket.getID() == id){
                outcome = ticket;
            }
        }
        return outcome;
    }

    public static Ticket[] getWhereStatus(String status){
        ArrayList<Ticket> statusTickets = new ArrayList<>();
        for (Ticket ticket:Ticket.tickets) {
            if (ticket.getTicketStatus().equals(status)) {
                statusTickets.add(ticket);
            }
        }
        return statusTickets.toArray(new Ticket[0]);
    }

    public static Ticket[] getWhereTicketCreated(Date created){
        ArrayList<Ticket> statusTickets = new ArrayList<>();
        for (Ticket ticket:Ticket.tickets) {
            if (ticket.getTicketCreated().equals(created)) {
                statusTickets.add(ticket);
            }
        }
        return statusTickets.toArray(new Ticket[0]);
    }

    public static Ticket[] getWhereTicketClosed(Date closed){
        ArrayList<Ticket> statusTickets = new ArrayList<>();
        for (Ticket ticket:Ticket.tickets) {
            if (ticket.getTicketclosed().equals(closed)) {
                statusTickets.add(ticket);
            }
        }
        return statusTickets.toArray(new Ticket[0]);
    }

    public static Ticket[] getTicketDateRange(String created, String closed) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
        Date createdDate = sdf.parse(created);
        Date closedDate = sdf.parse(closed);

        ArrayList<Ticket> ticketRange = new ArrayList<>();
        for (Ticket ticket: Ticket.tickets) {
            if (sdf.parse(ticket.dateCreated).after(createdDate) && sdf.parse(ticket.dateClosed).before(closedDate)) {
                ticketRange.add(ticket);
            }
        }
        return ticketRange.toArray(new Ticket[0]);
    }


    public static void analyseAndAssignTickets(ServiceDeskMember serviceDeskMember){

        if(serviceDeskMember.getTicketCount() < 3){

            ArrayList<Ticket> requiresAssignment = new ArrayList<>();

            for (Ticket ticket: Ticket.tickets){

                if(Objects.equals(ticket.assignedTo, "{{PENDING}}") && ticket.getTicketLevel().equals(serviceDeskMember.getServiceLevel())){
                    requiresAssignment.add(ticket);
                }

            }

            if(requiresAssignment.size() != 0){
                int assignableCount = (3- serviceDeskMember.getTicketCount());

                int i = 0;
                while(i < assignableCount){

                    if(i < requiresAssignment.size()){
                        requiresAssignment.get(i).setAssignedTo(serviceDeskMember.name);
                    }

                    i++;
                }
            }
        }

        XMLWriterService.saveAllTickets();
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

    public void setTicketStatus(String status){
        this.ticketStatus = status;
    }

    public int getID(){
        return Integer.parseInt(this.ticketId);
    }

    public String getTicketCreated() {
        return this.dateCreated;
    }

    public String getTicketclosed() {
        return this.dateClosed;
    }

    public void setTicketLevel(String ticketLevel) {
        this.ticketLevel = ticketLevel;
    }

    public void setTicketCreated(String created) {
        this.dateCreated = created;
    }

    public void setTicketClosed(String closed) {
        this.dateClosed = closed;
    }
}

