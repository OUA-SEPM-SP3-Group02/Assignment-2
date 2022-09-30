package com.sepm.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TicketManager {
    private Ticket[] tickets;
    private int numTickets;

    public TicketManager() {
        this.tickets = null;
        this.numTickets = 0;
    }

    private void addTicket(int ticketId, String issuer, String email, String level, String status) {
        this.tickets = new Ticket[5];
        this.tickets[this.numTickets] = new Ticket(ticketId, issuer, email, level, status);
        this.numTickets += 1;
    }

    public void loadTickets() throws IOException {
        BufferedReader ticketFile = new BufferedReader(new FileReader("tickets.csv"));
        String ticketFileLine = ticketFile.readLine();
        while (ticketFileLine != null) {
            String[] ticketFields = ticketFileLine.split(",");
            int ticketId = Integer.parseInt(ticketFields[0]);
            String issuer = ticketFields[1];
            String email = ticketFields[2];
            String level = ticketFields[3];
            String status = ticketFields[4];

            addTicket(ticketId, issuer, email, level, status);
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
