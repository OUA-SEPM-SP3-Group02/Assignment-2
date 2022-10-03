package com.sepm.controller;
import com.sepm.model.TicketManager;
import com.sepm.view.TicketManagerUI;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class TicketManagerApp {
    private TicketManager TicketManager;
    private TicketManagerUI userInterface;

    public TicketManagerApp () {
        this.TicketManager = new TicketManager();
        this.userInterface = new TicketManagerUI(this);
    }

    public static void main (String[] args){
        TicketManagerApp obj = new TicketManagerApp();
    }

    public String displayTickets() {
        String ticketDisplay;
        ticketDisplay = this.TicketManager.toString();
        return ticketDisplay;
    }

    public void loadTickets(String ticketsFile) throws ParserConfigurationException, IOException, SAXException {

        this.TicketManager.readXMLTickets(ticketsFile);
    }

    public String displayAllTickets() {
        String ticketData;
        ticketData = this.TicketManager.toString();
        return ticketData;
    }
}
