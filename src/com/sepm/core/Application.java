package com.sepm.core;

import com.sepm.controller.AuthenticationController;
import com.sepm.controller.TicketController;
import com.sepm.controller.UserController;
import com.sepm.model.ServiceDeskMember;
import com.sepm.model.StaffMember;
import com.sepm.model.Ticket;
import com.sepm.model.User;
import com.sepm.service.XMLLoaderService;

import java.util.HashMap;

public class Application {
    private final HashMap<String, Controller> controllers;
    private String activeController;
    private ServiceDeskMember serviceDeskUser;
    private StaffMember staffUser;

    public Application() {
        //create our controllers hashmap
        this.controllers = new HashMap<>();

        //register all our controllers with the hashmap
        this.controllers.put("userController", new UserController(this));
        this.controllers.put("authenticationController", new AuthenticationController(this));
        this.controllers.put("ticketController" , new TicketController(this));

        //set the active controller
        this.activeController = "authenticationController";

        //Bind our ticket data to our loaded tickets
        Ticket.bindTicketData(XMLLoaderService.loadTicketsFromXMLFile("tickets.xml"));
        User.bindServiceDeskUserData(XMLLoaderService.loadServiceDeskMembers("serviceDesk.xml"));
        User.bindStaffMemberUserData(XMLLoaderService.loadStaffMembers("staffMembers.xml"));

        //start the input loop
        processInput(new Request());
    }

    public void processInput(Request request) {
        this.controllers.get(this.activeController).processInput(request);
    }

    public void updateView(Response response) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        this.controllers.get(this.activeController).updateView(response);
    }

    public void setActiveController(String key) {
        this.activeController = key;
    }

    public void setActiveSubView(String view){
        this.controllers.get(this.activeController).setActiveSubView(view);
    }

    public void setServiceDeskUser(ServiceDeskMember user){
        this.serviceDeskUser = user;

        if(user != null){
            Ticket.analyseAndAssignTickets(user);
        }
    }

    public void setStaffUser(StaffMember user) {
        this.staffUser = user;
        if (user != null) {
            Ticket.setStaffTicketsForUser(user);
        }
    }

    public Controller getActiveController(){
        return this.controllers.get(this.activeController);
    }

    public ServiceDeskMember getServiceDeskUser(){
        return this.serviceDeskUser;
    }
}
