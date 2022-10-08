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


        switch (this.activeSubView) {
            case "mainMenu" -> response = this.mainMenu(request);
            case "showTickets" -> response = this.showTickets(request);
            case "showServiceMembers" -> response = this.showServiceMembers(request);
        }

        this.app.updateView(response);
    }

    private Response mainMenu(Request request){
        Response response = new Response();

        switch (request.get("input").toString()) {
            case "A" -> {this.activeSubView = "showTickets";response.add("service_level",this.app.getUser().getServiceLevel());response.add("tickets", Ticket.getWereLevel(this.app.getUser().getServiceLevel()));}
            case "B", "C", "D" -> response.add("error","Feature not yet added");
            case "E" -> {this.app.setActiveController("ticketController"); this.app.processInput(new Request());}
            default -> response.add("error", "Invalid input, please select A, B or C!");
        }

        return response;
    }

    private Response showTickets(Request request){
        Response response = new Response();

        if(request.containsUserInput()){
            if(request.get("input").equals("X")){
                this.activeSubView = "mainMenu";
                response.add("notification","Returned "+this.app.getUser().getName()+" to the main menu from show tickets menu");
                return response;
            }
        }

        response.add("service_level",this.app.getUser().getServiceLevel());
        response.add("tickets", Ticket.getWereLevel(this.app.getUser().getServiceLevel()));

        return response;
    }

    private Response showServiceMembers(Request request){
        Response response = new Response();
        return response;
    }

}
