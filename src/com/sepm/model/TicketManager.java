package com.sepm.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TicketManager {
    private Ticket[] tickets;
    private int numTickets;

    public TicketManager() {
        this.tickets = null;
        this.numTickets = 0;
    }

    private void addTicket(int ticketId, String title, String description, String issuer, String email, String level, String status) {
        this.tickets = new Ticket[5];
        this.tickets[this.numTickets] = new Ticket(ticketId, title, description, issuer, email, level, status);
        this.numTickets += 1;
    }

    public void loadTickets() throws IOException {
        BufferedReader ticketFile = new BufferedReader(new FileReader("tickets.csv"));
        String ticketFileLine = ticketFile.readLine();
        while (ticketFileLine != null) {
            String[] ticketFields = ticketFileLine.split(",");
            int ticketId = Integer.parseInt(ticketFields[0]);
            String title = ticketFields[1];
            String description = ticketFields[2];
            String issuer = ticketFields[3];
            String email = ticketFields[4];
            String level = ticketFields[5];
            String status = ticketFields[6];

            addTicket(ticketId, title, description, issuer, email, level, status);
            ticketFileLine = ticketFile.readLine();
        }
        ticketFile.close();
    }

    public String toString() {
        String summary = "";
        int i = 0;
        while (i < this.numTickets) {
            summary += this.tickets[i] + "\n";
            i += 1;
        }
        return summary;
    }
}
