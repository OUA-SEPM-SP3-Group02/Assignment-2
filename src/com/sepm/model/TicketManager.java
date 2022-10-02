package com.sepm.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.*;

import org.xml.sax.*;
import org.w3c.dom.*;

public class TicketManager {
    private Ticket[] tickets;
    private int numTickets;
    private String id = null;
    private String title = null;
    private String description = null;
    private String issuedBy = null;
    private String email = null;
    private String level = null;
    private String status = null;
    private ArrayList<String> ticket;

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

    public boolean readXML(String xml) {
        ticket = new ArrayList<String>();
        Document dom;
        // Make an  instance of the DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // use the factory to take an instance of the document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // parse using the builder to get the DOM mapping of the
            // XML file
            dom = db.parse(xml);

            Element doc = dom.getDocumentElement();

            id = getTextValue(id, doc, "id");
            if (id != null) {
                if (!id.isEmpty())
                    ticket.add(id);
            }
            title = getTextValue(title, doc, "title");
            if (title != null) {
                if (!title.isEmpty())
                    ticket.add(title);
            }
            description = getTextValue(description, doc, "description");
            if (description != null) {
                if (!description.isEmpty())
                    ticket.add(description);
            }
            issuedBy = getTextValue(issuedBy, doc, "issuedBy");
            if ( issuedBy != null) {
                if (!issuedBy.isEmpty())
                    ticket.add(issuedBy);
            }
            email = getTextValue(email, doc, "email");
            if ( email != null) {
                if (!email.isEmpty())
                    ticket.add(email);
            }
            level = getTextValue(level, doc, "level");
            if ( level != null) {
                if (!level.isEmpty())
                    ticket.add(level);
            }
            status = getTextValue(status, doc, "status");
            if ( status != null) {
                if (!status.isEmpty())
                    ticket.add(status);
            }
            return true;

        } catch (ParserConfigurationException pce) {
            System.out.println(pce.getMessage());
        } catch (SAXException se) {
            System.out.println(se.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }

        return false;
    }

    private String getTextValue(String def, Element doc, String tag) {
        String value = def;
        NodeList nl;
        nl = doc.getElementsByTagName(tag);
        if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
            value = nl.item(0).getFirstChild().getNodeValue();
        }
        return value;
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
