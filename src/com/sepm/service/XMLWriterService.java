package com.sepm.service;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import com.sepm.model.Ticket;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

// Currenlty duplicates entries, so loads existing entries and writes them back atthe end with anything new.
// Need to work out how to avoid this.
public class XMLWriterService {

    public static void saveTicketToXML(String id, String title, String description, String issuedBy, String email, String level, String status, String xml) {
        Document dom;
        Element e;


        // instance of a DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        Document document;
        try {
            documentBuilder = dbf.newDocumentBuilder();
            document = documentBuilder.parse(xml);
        } catch (ParserConfigurationException | SAXException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        Element root = document.getDocumentElement();

        Collection<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket(id, title, description, issuedBy, email, level, status));

        for (Ticket ticket : tickets) {
            // server elements
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
    }



   /* public class AddXmlNode {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse("tickets.xml");
        Element root = document.getDocumentElement();


             try {
            // use factory to get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // create instance of DOM
            dom = db.newDocument();

            // create the root element
            Element rootEle = dom.createElement("ticket");

            // create data elements and place them under root
            *//*e = dom.createElement("id");
            e.appendChild(dom.createTextNode(id));
            rootEle.appendChild(e);*//*

            e = dom.createElement("title");
            e.appendChild(dom.createTextNode(title));
            rootEle.appendChild(e);

            e = dom.createElement("description");
            e.appendChild(dom.createTextNode(description));
            rootEle.appendChild(e);

           *//* e = dom.createElement("issuedBy");
            e.appendChild(dom.createTextNode(issuedBy));
            rootEle.appendChild(e);*//*

            *//*e = dom.createElement("email");
            e.appendChild(dom.createTextNode(email));
            rootEle.appendChild(e);*//*

            e = dom.createElement("level");
            e.appendChild(dom.createTextNode(level));
            rootEle.appendChild(e);

            e = dom.createElement("status");
            e.appendChild(dom.createTextNode(status));
            rootEle.appendChild(e);

            dom.appendChild(rootEle);

            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                //tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                // send DOM to file
                tr.transform(new DOMSource(dom),
                        new StreamResult(new FileOutputStream(xml)));

            } catch (TransformerException te) {
                System.out.println(te.getMessage());
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        } catch (ParserConfigurationException pce) {
            System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
        }
    }*/


    }

