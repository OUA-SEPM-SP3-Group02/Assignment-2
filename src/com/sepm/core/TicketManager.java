package com.sepm.core;

import com.sepm.controller.AuthenticationController;
import com.sepm.controller.UserController;
import com.sepm.model.Ticket;
import com.sepm.service.TicketService;

import java.util.HashMap;

public class TicketManager {
    private HashMap<String, Controller> controllers;
    private String activeController;

    public TicketManager() {
        //create our controllers hashmap
        this.controllers = new HashMap<>();

        //register all our controllers with the hashmap
        this.controllers.put("userController", new UserController(this));
        this.controllers.put("authenticationController", new AuthenticationController(this));

        //set the active controller
        this.activeController = "userController";

        //Bind our ticket data to our loaded tickets
        Ticket.bindTicketData(TicketService.loadTicketsFromXMLFile("tickets.xml"));

        //start the input loop
        processInput(new Request());
    }

    public void processInput(Request request) {
        this.controllers.get(this.activeController).processInput(request);
    }

    public void updateView(Response response) {
        this.controllers.get(this.activeController).updateView(response);
    }

    public void setActiveController(String key) {
        this.activeController = key;
    }
}
