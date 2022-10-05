package com.sepm.login;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;




public class Login {

    private static final Scanner sc = new Scanner(System.in);



    public static void login() throws IOException, SAXException, ParserConfigurationException {
        File loginDetails = new File ("serviceDesk.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(loginDetails);

        System.out.println("Email: ");
        String email = sc.next();
        System.out.println("Password: ");
        String password = sc.next();

        User x = new User();
        HashMap<String, String> loginuser = x.getUserMap(); // access hashmap

        find(loginuser, email, password);
    }

    private static void getAllTechnicians(Document doc)
    {
        NodeList staffNodes = doc.getElementsByTagName("serviceDesk");
        for(int i=0; i<staffNodes.getLength(); i++)
        {
            Node staffNode = staffNodes.item(i);
            if(staffNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element staffElement = (Element) staffNode;
                String staffId = staffElement.getElementsByTagName("email").item(0).getTextContent();
                String studentName = staffElement.getElementsByTagName("password").item(0).getTextContent();

            }
        }
    }

    public static void find(HashMap<String, String> loginUser, String email, String password) throws IOException, ParserConfigurationException, SAXException {
        for (String i : loginUser.keySet()) {
            if (i.equals(email) && loginUser.get(i).equals(password)) {
                System.out.println("User is Found!");
                System.out.println("Program will now terminate");
                System.exit(0);
                break;
            }
        }
        System.out.println("User Not Found. Login Again");
        login();
    }



}


