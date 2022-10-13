package com.sepm.service;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import com.sepm.model.Ticket;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

// Currently duplicates entries, so loads existing entries and writes them back at the end with anything new.
// Need to work out how to avoid this.
public class XMLWriterService {

    public static void saveTicketToXML(String id, String title, String description, String issuedBy, String email, String level, String status, String assignedTo, String xml) {
        try {
            // instance of a DocumentBuilderFactory
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document document = documentBuilder.parse(xml);
            Element root = document.getDocumentElement();

            // Creating a collection of tickets.
            Collection<Ticket> tickets = new ArrayList<>();

            // Adding a new tickets to the tickets ArrayList. The Ticket class' constructor requires 7 parameters.
            tickets.add(new Ticket(id, title, description, issuedBy, email, level, status, assignedTo));

            for (Ticket ticket : tickets) {
                //Fetching and adding ticket elements
                Element newTicket = document.createElement("ticket");

                Element ticketId = document.createElement("id");
                ticketId.appendChild(document.createTextNode(ticket.getTicketId()));
                newTicket.appendChild(ticketId);

                Element ticketTitle = document.createElement("title");
                ticketTitle.appendChild(document.createTextNode(ticket.getTicketTitle()));
                newTicket.appendChild(ticketTitle);

                Element ticketDescription = document.createElement("description");
                ticketDescription.appendChild(document.createTextNode(ticket.getTicketDescription()));
                newTicket.appendChild(ticketDescription);

                Element ticketIssuer = document.createElement("issuedBy");
                ticketIssuer.appendChild(document.createTextNode(ticket.getTicketIssuer()));
                newTicket.appendChild(ticketIssuer);

                Element ticketEmail = document.createElement("email");
                ticketEmail.appendChild(document.createTextNode(ticket.getTicketEmail()));
                newTicket.appendChild(ticketEmail);

                Element ticketLevel = document.createElement("level");
                ticketLevel.appendChild(document.createTextNode(ticket.getTicketLevel()));
                newTicket.appendChild(ticketLevel);

                Element ticketStatus = document.createElement("status");
                ticketStatus.appendChild(document.createTextNode(ticket.getTicketStatus()));
                newTicket.appendChild(ticketStatus);

                Element ticketAssignedTo = document.createElement("assigned");
                ticketStatus.appendChild(document.createTextNode(ticket.getAssignedTo()));
                newTicket.appendChild(ticketAssignedTo);

                root.appendChild(newTicket);
            }
            DOMSource source = new DOMSource(document);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer;
            try {
                transformer = transformerFactory.newTransformer();
            } catch (TransformerConfigurationException ex) {
                throw new RuntimeException(ex);
            }
            StreamResult result = new StreamResult(xml);
            try {
                transformer.transform(source, result);
            } catch (TransformerException ex) {
                throw new RuntimeException(ex);
            }
        } catch (ParserConfigurationException | SAXException ex) {
            //throw new RuntimeException(ex);
            System.out.println("Could not parse XM file");
        } catch (IOException ex) {
            //throw new RuntimeException(ex);
            System.out.println("Could not read XML file.");
        }
    }
}

