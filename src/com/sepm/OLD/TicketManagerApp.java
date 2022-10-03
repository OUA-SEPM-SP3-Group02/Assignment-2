package com.sepm.OLD;
import com.sepm.core.TicketManager;
//import com.sepm.OLD.TicketManager;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class TicketManagerApp {
    private TicketManager ticketManager;
    private TicketManagerUI userInterface;

    public TicketManagerApp () {
        this.ticketManager = new TicketManager();
        this.userInterface = new TicketManagerUI(this);
    }

    public static void main (String[] args){
        TicketManagerApp obj = new TicketManagerApp();
    }

    public void loadTickets(String ticketsFile) throws ParserConfigurationException, IOException, SAXException {
        //this.ticketManager.loadTicketsFromXMLFile(ticketsFile);
    }

    public String displayAllTickets() {
        String ticketData;
        ticketData = this.ticketManager.toString();
        return ticketData;
    }
}
