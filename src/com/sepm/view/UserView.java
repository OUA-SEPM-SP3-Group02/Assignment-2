package com.sepm.view;

import com.sepm.core.Request;
import com.sepm.core.Response;
import com.sepm.core.View;
import com.sepm.model.ServiceDeskMember;
import com.sepm.model.Ticket;

public class UserView extends View {

    public Request mainMenu(Response response) {
        System.out.println("Welcome to the Helpdesk Application.\n");
        System.out.println("Main Menu:");
        Request request = new Request();

        if (response.contains("notification")) {
            System.out.println(response.get("notification"));
        }

        String menu = "";
        menu += "A - Display all tickets\n";
        menu += "B - Display open tickets\n";
        menu += "C - Display closed tickets\n";
        menu += "D - List service desk members\n";
        menu += "E - Create new service Ticket\n";

        System.out.println(menu);

        if (response.contains("error")) {
            //if so output it
            System.out.println(response.get("error"));
        }

        System.out.println("Enter your choice: ");
        request.add("input", this.getUserInput());

        return request;

    }

    public Request showTickets(Response response) {
        //SHOW HEADING
        System.out.println("Show tickets.");

        //create our new request
        Request request = new Request();


        if (response.contains("Notification")) {
            System.out.println(response.get("Notification"));
        }

        //check if the response contains tickets
        if (response.contains("tickets")) {
            System.out.printf("%-5s %-50s %-50s %-20s %-20s %-10s %-10s\n", "ID", "Title", "Description", "Issuer", "Email", "Level", "Status");
            //if so then for each ticket output them
            for (Ticket ticket : (Ticket[]) response.get("tickets")) {
                //System.out.println(ticket.toString());
                String[] explodedArray = ticket.toString().split(":");


                System.out.printf("%-5s %-50s %-50s %-20s %-20s %-10s %-10s \n",
                        explodedArray[0],
                        explodedArray[1].substring(0, Math.min(explodedArray[1].length(), 40)) + "...",
                        explodedArray[2].substring(0, Math.min(explodedArray[2].length(), 40)) + "...",
                        explodedArray[3],
                        explodedArray[4],
                        explodedArray[5],
                        explodedArray[6]);

            }
        }

        //check if the response contains an error
        if (response.contains("error")) {
            //if so output it
            System.out.println(response.get("error"));
        }

        //add our user input into the request
        request.add("input", this.getUserInput());

        return request;
    }

    public Request showServiceDeskMembers(Response response) {
        //SHOW HEADING
        System.out.println("Show service desk members.");

        //create our new request
        Request request = new Request();


        if (response.contains("Notification")) {
            System.out.println(response.get("Notification"));
        }

        //check if the response contains tickets
        if (response.contains("serviceDeskMembers")) {
            System.out.printf("%-5s %-20s %-30s %-8s %-20s\n", "ID", "Name", "Email", "Level", "Password");
            //if so then for each ticket output them
            for (ServiceDeskMember serviceDeskMember : (ServiceDeskMember[]) response.get("serviceDeskMembers")) {
                //System.out.println(ticket.toString());
                String[] explodedArray = serviceDeskMember.toString().split(":");

                System.out.printf("%-5s %-20s %-30s %-8s %-20s\n",
                        explodedArray[0],
                        explodedArray[1],
                        explodedArray[2],
                        explodedArray[3],
                        explodedArray[4]
                );
            }
        }

        //check if the response contains an error
        if (response.contains("error")) {
            //if so output it
            System.out.println(response.get("error"));
        }

        //add our user input into the request
        request.add("input", this.getUserInput());

        return request;
    }


}
