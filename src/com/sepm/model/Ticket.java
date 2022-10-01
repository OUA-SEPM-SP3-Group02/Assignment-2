package com.sepm.model;

class Ticket {
    private int ticketId;
    private String ticketIssuer;
    private String ticketEmail;
    private String ticketLevel;
    private String ticketStatus;

    Ticket(int id, String issuer, String email, String level, String status) {
        this.ticketId = id;
        this.ticketIssuer = issuer;
        this.ticketEmail = email;
        this.ticketLevel = level;
        this.ticketStatus = status;
    }

    public String toString() {
        String ticketSummary = "";
        ticketSummary += this.ticketId + ",";
        ticketSummary += this.ticketIssuer + ",";
        ticketSummary += this.ticketEmail + ",";
        ticketSummary += this.ticketLevel + ",";
        ticketSummary += this.ticketStatus;
        return ticketSummary;
    }

    // Test to see if commits are working - Pete
}

