package com.sepm.service;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import com.sepm.core.Request;
import com.sepm.core.Response;
import com.sepm.model.StaffMember;
import com.sepm.model.Ticket;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.io.File;
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

            Ticket[] existingTickets = XMLLoaderService.loadTicketsFromXMLFile("tickets.xml");

            int i = 0;
            while (i < existingTickets.length) {
                String existingId = existingTickets[i].getTicketId();
                String existingTitle = existingTickets[i].getTicketTitle();
                String existingDescription = existingTickets[i].getTicketDescription();
                String existingIssuer = existingTickets[i].getTicketIssuer();
                String existingEmail = existingTickets[i].getTicketEmail();
                String existingLevel = existingTickets[i].getTicketLevel();
                String existingStatus = existingTickets[i].getTicketStatus();
                String existingAssigned = existingTickets[i].getAssignedTo();

                tickets.add(new Ticket(existingId, existingTitle, existingDescription, existingIssuer, existingEmail, existingLevel, existingStatus, existingAssigned));

                i +=1;
            }

            // Adding a new ticket to the tickets ArrayList. The Ticket class' constructor requires 7 parameters.
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
            StreamResult result = new StreamResult(new File("tickets_new.xml"));
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

    public static void saveStaffMemberToXML(String id, String name, String email, String phone, String password, String xml) {
        try {
            // instance of a DocumentBuilderFactory
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document document = documentBuilder.parse(xml);
            Element root = document.getDocumentElement();

            // Creating a collection of tickets.
            Collection<StaffMember> staffMembers = new ArrayList<>();

            // Adding a new staff member to the StaffMember ArrayList. The StaffMember class' constructor requires 5 parameters.
            staffMembers.add(new StaffMember(id, name, email, phone, password));

            for (StaffMember staffMember : staffMembers) {
                //Fetching and adding ticket elements
                Element newStaffMember = document.createElement("StaffMember");

                Element registerId = document.createElement("id");
                registerId.appendChild(document.createTextNode(staffMember.getId()));
                newStaffMember.appendChild(registerId);

                Element registerFullName = document.createElement("name");
                registerFullName.appendChild(document.createTextNode(staffMember.getName()));
                newStaffMember.appendChild(registerFullName);

                Element registerEmail = document.createElement("email");
                registerEmail.appendChild(document.createTextNode(staffMember.getEmail()));
                newStaffMember.appendChild(registerEmail);

                Element registerPhNumber = document.createElement("phone");
                registerPhNumber.appendChild(document.createTextNode(staffMember.getPhone()));
                newStaffMember.appendChild(registerPhNumber);

                Element registerPassword = document.createElement("password");
                registerPassword.appendChild(document.createTextNode(staffMember.getPassword()));
                newStaffMember.appendChild(registerPassword);



                root.appendChild(newStaffMember);
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

