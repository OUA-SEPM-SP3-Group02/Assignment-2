package com.sepm.service;

import com.sepm.model.Ticket;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class TicketService {
    private static ArrayList<Ticket> tickets = new ArrayList<>();

    public static ArrayList<Ticket> getTickets() {
        return TicketService.tickets;
    }

    public static boolean loadTicketsFromXMLFile(String xml) {
        String ticketId = "";
        String title = "";
        String description = "";
        String issuedBy = "";
        String email = "";
        String level = "";
        String status = "";
        boolean outcome = true;

        try {
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

                    TicketService.tickets.add(new Ticket(ticketId, title, description, issuedBy, email, level, status));
                }
            }
        } catch (ParserConfigurationException e) {
            outcome = false;
        } catch (IOException e) {
            outcome = false;
        } catch (SAXException e) {
            outcome = false;
        }
        return outcome;
    }
}
