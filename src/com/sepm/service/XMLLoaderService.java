package com.sepm.service;

import com.sepm.model.ServiceDeskMember;
import com.sepm.model.StaffMember;
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


public class XMLLoaderService {

    public static Ticket[] loadTicketsFromXMLFile(String xml) {

        ArrayList<Ticket> tickets = new ArrayList<>();

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
                    String ticketId = tElement.getElementsByTagName("id").item(0).getTextContent();
                    String title = tElement.getElementsByTagName("title").item(0).getTextContent();
                    String description = tElement.getElementsByTagName("description").item(0).getTextContent();
                    String issuedBy = tElement.getElementsByTagName("issuedBy").item(0).getTextContent();
                    String email = tElement.getElementsByTagName("email").item(0).getTextContent();
                    String level = tElement.getElementsByTagName("level").item(0).getTextContent();
                    String status = tElement.getElementsByTagName("status").item(0).getTextContent();
                    String assignedTo = tElement.getElementsByTagName("assigned").item(0).getTextContent();

                    tickets.add(new Ticket(ticketId, title, description, issuedBy, email, level, status, assignedTo));
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException ignored) {
            System.out.println("ERROR: Failed to load data");
        }

        Ticket[] ticketData = new Ticket[0];
        return tickets.toArray(ticketData);
    }

    public static ServiceDeskMember[] loadServiceDeskMembers(String xml) {
        String id = "";
        String name = "";
        String email = "";
        String level = "";
        String password = "";
        ArrayList<ServiceDeskMember> serviceDeskMembers = new ArrayList<>();

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
            NodeList nodeList = doc.getElementsByTagName("serviceDesk");

            // Iterate through all the nodes in NodeList using a for loop.
            for (int i = 0; i < nodeList.getLength(); ++i) {
                Node node = nodeList.item(i);
                //System.out.println("\nNode Name :" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element tElement = (Element) node;
                    id = tElement.getElementsByTagName("id").item(0).getTextContent();
                    name = tElement.getElementsByTagName("name").item(0).getTextContent();
                    level = tElement.getElementsByTagName("level").item(0).getTextContent();
                    email = tElement.getElementsByTagName("email").item(0).getTextContent();
                    password = tElement.getElementsByTagName("password").item(0).getTextContent();

                    serviceDeskMembers.add(new ServiceDeskMember(id, name, level, email, password));
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException ignored) {
            System.out.println("ERROR: Failed to load data");
        }

        ServiceDeskMember[] serviceDeskMembersData = new ServiceDeskMember[0];
        return serviceDeskMembers.toArray(serviceDeskMembersData);
    }

    public static StaffMember[] loadStaffMembers(String xml){
        String id = "";
        String name = "";
        String email = "";
        String phone = "";
        String password = "";
        ArrayList<StaffMember> staffMembers = new ArrayList<>();
        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(xml);

            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            // Here nodeList contains all the nodes with name ticket.
            NodeList nodeList = doc.getElementsByTagName("staffMember");

            // Iterate through all the nodes in NodeList using a for loop.
            for (int i = 0; i < nodeList.getLength(); ++i) {
                Node node = nodeList.item(i);
                //System.out.println("\nNode Name :" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element tElement = (Element) node;
                    id = tElement.getElementsByTagName("id").item(0).getTextContent();
                    name = tElement.getElementsByTagName("name").item(0).getTextContent();
                    email = tElement.getElementsByTagName("email").item(0).getTextContent();
                    phone = tElement.getElementsByTagName("phone").item(0).getTextContent();
                    password = tElement.getElementsByTagName("password").item(0).getTextContent();

                    staffMembers.add(new StaffMember(id, name, email, phone, password));
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException ignored) {
            System.out.println("ERROR: Failed to load data");
        }

        StaffMember[] staffMembersData = new StaffMember[0];
        return staffMembers.toArray(staffMembersData);
    }
}

