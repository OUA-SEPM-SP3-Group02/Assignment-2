package com.sepm.model;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.io.IOException;

public class Ticketmanager {
    private Ticket[] tickets;
    private int numTickets;
    // private ArrayList<String> ticket;

    public Ticketmanager() {
        this.tickets = new Ticket[10];
        this.numTickets = 0;
    }

    private void addTicket(String ticketId, String title, String description, String issuer, String email, String level, String status) {
        this.tickets[this.numTickets] = new Ticket(ticketId, title, description, issuer, email, level, status);
        this.numTickets += 1;
    }

    public void loadTicketsFromXMLFile(String xml) throws ParserConfigurationException, IOException, SAXException {
        String ticketId = "";
        String title = "";
        String description = "";
        String issuedBy = "";
        String email = "";
        String level = "";
        String status = "";

            // Defines a factory API that enables
            // applications to obtain a parser that produces
            // DOM object trees from XML documents.
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            // we are creating an object of builder to parse the  xml file.
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(xml);

            /*here normalize method Puts all Text nodes in the full depth of the sub-tree underneath this
            Node, including attribute nodes, into a "normal" form where only structure separates
            Text nodes, i.e., there are neither adjacent Text nodes nor empty Text nodes. */

            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            // Here nodeList contains all the nodes with name ticket.
            NodeList nodeList = doc.getElementsByTagName("ticket");

            // Iterate through all the nodes in NodeList using a for loop.
            for (int i = 0; i < nodeList.getLength(); ++i) {
                Node node = nodeList.item(i);
                //System.out.println("\nNode Name :" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element tElement = (Element) node;
                    ticketId = tElement.getElementsByTagName("id").item(0).getTextContent();
                    title = tElement.getElementsByTagName("title").item(0).getTextContent();
                    description = tElement.getElementsByTagName("description").item(0).getTextContent();
                    issuedBy = tElement.getElementsByTagName("issuedBy").item(0).getTextContent();
                    email = tElement.getElementsByTagName("email").item(0).getTextContent();
                    level = tElement.getElementsByTagName("level").item(0).getTextContent();
                    status = tElement.getElementsByTagName("status").item(0).getTextContent();
                    addTicket(ticketId, title, description, issuedBy, email, level, status);
                }
            }
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
