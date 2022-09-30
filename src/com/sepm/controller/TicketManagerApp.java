package com.sepm.controller;
import com.sepm.model.TicketManager;

public class TicketManagerApp {
    private TicketManager TicketManager;

    public static void main (String[] args){
        TicketManagerApp obj = new TicketManagerApp();
    }

    public String displayTickets() {
        String ticketDisplay;
        ticketDisplay = this.TicketManager.toString();
        return ticketDisplay;
    }
}
