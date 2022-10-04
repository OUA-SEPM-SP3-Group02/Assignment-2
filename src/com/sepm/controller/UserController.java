package com.sepm.controller;

import com.sepm.core.Controller;
import com.sepm.core.Request;
import com.sepm.core.Response;
import com.sepm.core.Application;
import com.sepm.model.ServiceDeskMember;
import com.sepm.model.Ticket;
import com.sepm.view.UserView;

public class UserController extends Controller {
    public UserController(Application ticketManager) {
        super(ticketManager);
        this.view = new UserView();
        this.activeSubView = "mainMenu";
    }

    @Override
    public void updateView(Response response) {
        Request request = new Request();
        switch(this.activeSubView) {
            case "mainMenu" -> request =  ((UserView)this.view).mainMenu(response);
            case "showTickets" -> request = ((UserView)this.view).showTickets(response);
            case "showServiceMembers" -> request = ((UserView)this.view).showServiceDeskMembers(response);
        }

        this.app.processInput(request);


    }

    @Override
    public void processInput(Request request) {
        Response response = new Response();

        if(this.activeSubView == "mainMenu") {
            if (request.containsUserInput()) {
                switch (request.get("input").toString()) {
                    case "A" -> activeSubView = "showTickets";
                    case "B" -> System.out.println("B selected");
                    case "C" -> System.out.println("C selected");
                    case "D" -> activeSubView = "showServiceMembers";
                    default -> response.add("error", "Invalid input, please select A, B or C!");
                }
            }
        }
        if (this.activeSubView == "showTickets") {
            //Add our tickets to the response
            response.add("tickets", Ticket.getAll());

            if (request.containsUserInput()) {
                if (request.get("input").equals("hello")) {
                    response.add("Notification", "User entered a valid input.");
                } else {
                    response.add("error", "Invalid input, user input must be hello.");
                }
            }

        }

        if (this.activeSubView == "showServiceMembers") {
            response.add("serviceDeskMembers", ServiceDeskMember.getServiceDeskMembers());
        }

        if (this.activeSubView == "openTickets") {
            response.add("tickets", Ticket.getWereLevel("open"));
        }



        this.app.updateView(response);
    }
}
