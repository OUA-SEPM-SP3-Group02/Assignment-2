package com.sepm.view;

import com.sepm.controller.TicketManagerApp;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TicketManagerUI {
    private TicketManagerApp ticketManagerApp;
    private static Scanner stdin = new Scanner(System.in);

    public TicketManagerUI(TicketManagerApp ticketManagerApp) {
        this.ticketManagerApp = ticketManagerApp;

        try {
            this.ticketManagerApp.loadTickets("tickets.xml");
            System.out.println("Loading tickets...");

        } catch (IOException e) {
            System.out.println("File not found.");
            System.out.println();
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        } catch (SAXException e) {
            System.out.println(e.getMessage());
        }


        String ticketListing = this.ticketManagerApp.displayAllTickets();
        displayTickets(ticketListing);

    }

    private static void displayTickets(String ticketList) {
        String[] ticketListDetails = ticketList.split("\n");

        int i = 0;
        while (i<ticketListDetails.length) {
            String[] ticketDetails = ticketListDetails[i].split(":");

            String ticketId = ticketDetails[0];
            String title = ticketDetails[1];
            String description = ticketDetails[2];
            String issuedBy = ticketDetails[3];
            String email = ticketDetails[4];
            String level = ticketDetails[5];
            String status = ticketDetails[6];

            System.out.println("Ticket ID: " + ticketId);
            System.out.println("Title: " + title);
            System.out.println("Description: " + description);
            System.out.println("Issuer: " + issuedBy);
            System.out.println("Email: " + email);
            System.out.println("Severity: " + level);
            System.out.println("Status: " + status);
            System.out.println();

            i += 1;
        }

    }

    public void createTicket() {
        // Why these variables public?
        int ticketId;
        String title;
        String description;
        String issuer;
        String email;
        String level;
        String status;
        System.out.println("Please provide details of the issue: ");
        description = stdin.nextLine();


    }

    // Input function with check for empty input value.
    private static String getInput(String prompt) {


        String userInputValue = "";
        boolean isInputEmpty = false;


        while (!isInputEmpty) {

            // First, display the received parameter.
            System.out.print(prompt);

            // Now the user can enter a value via the stdin variable which is an object of the Scanner class. To be
            // on the safe side, the input gets also stripped. The read value is stored in a variable called
            // userInput of type String. Because the Scanner class has been initialised as a static object, and hence the
            // stdin object belongs to the class itself, the classname is used to call the object rather than "this".
            String userInput = TicketManagerUI.stdin.nextLine().strip();

            // Now the code checks if the received input contains anything or is blank. If it is blank, the user will
            // be prompted with a message.
            if (userInput.isBlank()) {
                System.out.println("Input cannot be blank.");

                // if the input is not empty, the value can be returned to the caller by assigning the value to the
                // userInputValue variable that later gets returned from the method. And to exit the loop, the
                // isInputEmpty variable can be set to true.
            } else {
                userInputValue = userInput;
                isInputEmpty = true;
            }
        }
        return userInputValue;
    }
}
