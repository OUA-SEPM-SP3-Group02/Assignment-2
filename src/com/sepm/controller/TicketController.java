package com.sepm.controller;

import com.sepm.core.Application;
import com.sepm.core.Controller;
import com.sepm.core.Request;
import com.sepm.core.Response;
import com.sepm.service.XMLWriterService;
import com.sepm.view.TicketView;

public class TicketController extends Controller {

    private String ticketTitle;
    private String ticketDescription;
    private String ticketLevel;
    private String ticketStatus;

    public TicketController(Application ticketManager) {
        super(ticketManager);
        this.view = new TicketView();
        this.activeSubView = "addNewTicket";
    }

    @Override
    public void updateView(Response response) {
        Request request = new Request();
        switch (this.activeSubView) {
            case "addNewTicket" -> request = ((TicketView) this.view).addNewTicket(response);
        }
        this.app.processInput(request);
    }

    @Override
    public void processInput(Request request) {
        Response response = new Response();
        switch (this.activeSubView) {
            case "addNewTicket" -> response = this.addNewTicket(request);
        }
        this.app.updateView(response);
    }

    private Response addNewTicket(Request request) {
        Response response = new Response();

        if (this.ticketTitle == null) {
            response.add("heading", "Please enter you ticket title: ");
            if (request.containsUserInput()) {
                this.ticketTitle = (String) request.get("input");
                response.add("heading", "Please enter you ticket description: ");
                response.add("ticketTitle", this.ticketTitle);
            } else {
                response.add("error", "Error! Please enter a title");
            }
            return response;
        } else {
            response.add("ticketTitle", this.ticketTitle);
        }

        if (this.ticketDescription == null) {
            response.add("heading", "Please enter you ticket description: ");
            if (request.containsUserInput()) {
                this.ticketDescription = (String) request.get("input");
                response.add("heading", "Please enter you ticket level: ");
                response.add("ticketDescription", this.ticketDescription);
            } else {
                response.add("error", "Error! Please enter a description");
            }
            return response;
        } else {
            response.add("ticketDescription", this.ticketDescription);
        }

        if (this.ticketLevel == null) {
            response.add("heading", "Please enter you ticket level: ");
            if (request.containsUserInput()) {
                this.ticketLevel = (String) request.get("input");
                response.add("heading", "Please review and confirm these (y/n): ");
                response.add("ticketLevel", this.ticketLevel);
            } else {
                response.add("error", "Error! Please enter a level");
            }
            return response;
        } else {
            response.add("ticketLevel", this.ticketLevel);
        }

        if (this.ticketStatus == null) {
            response.add("ticketStatus", "open");
        }

        if (request.containsUserInput()) {
            switch (request.get("input").toString()) {
                case "Y" -> {
                    System.out.println("Feature not yet added");
                    String name = this.app.getUser().getName();
                    String email = this.app.getUser().getEmail();
                    String title = (String) response.get("ticketTitle");
                    String description = (String) response.get("ticketDescription");
                    String level = (String) response.get("ticketLevel");
                    String status = (String) response.get("ticketStatus");
                    String assignedTo = (String) response.get("assigned");
                    // Calling XMLWriterService.saveTicketToXML have to work out how to increment ID
                    XMLWriterService.saveTicketToXML("1", title, description, name, email, level, status, assignedTo, "tickets.xml");
                }
                case "N" -> {
                    this.ticketTitle = null;
                    this.ticketDescription = null;
                    this.ticketLevel = null;
                    this.app.setActiveController("userController");
                    response.add("notification", "New ticket creation cancelled");
                    this.app.updateView(response);
                }
                default -> response.add("error", "Invalid input!! Please input Y or N to continue: ");
            }
        }
        return response;
    }

    private Response changeTicketLevel(Request request) {
        Response response = new Response();

    }

}
