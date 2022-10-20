package com.sepm.controller;

import com.sepm.core.Controller;
import com.sepm.core.Request;
import com.sepm.core.Response;
import com.sepm.core.Application;
import com.sepm.model.Ticket;
import com.sepm.view.UserView;

import java.text.ParseException;

public class UserController extends Controller {
    public UserController(Application ticketManager) {
        super(ticketManager);
        this.view = new UserView();
        this.activeSubView = "mainMenu";
    }

    @Override
    public void updateView(Response response) {
        Request request = new Request();
        switch (this.activeSubView) {
            case "mainMenu" -> request = ((UserView) this.view).mainMenu(response);
            case "showServiceMembers" -> request = ((UserView) this.view).showServiceDeskMembers(response);
            case "selectTicket" -> request = ((UserView) this.view).selectTicket(response);
            case "showTicketDateRange" ->request= ((UserView) this.view).showTicketDateRange(response);
        }

        this.app.processInput(request);
    }

    @Override
    public void processInput(Request request) {
        Response response = new Response();


        switch (this.activeSubView) {
            case "mainMenu" -> response = this.mainMenu(request);
            case "showServiceMembers" -> response = this.showServiceMembers(request);
            case "selectTicket" -> response = this.selectTicket(request);
            case "showTicketDateRange" -> response = this.showTicketDateRange(request);
        }

        this.app.updateView(response);
    }

    private Response mainMenu(Request request) {
        Response response = new Response();
        response.add("tickets", Ticket.getWhereName(this.app.getServiceDeskUser().getName()));


        switch (request.get("input").toString()) {
            case "A" -> this.activeSubView = "selectTicket";
            case "B", "C", "D" -> response.add("error", "Feature not yet added");
            case "E" -> {
                this.app.setActiveController("ticketController");
                this.app.processInput(new Request());
            }
            case "F" -> this.activeSubView = "showTicketDateRange";
            case "X" -> {
                this.app.setActiveController("authenticationController");
                this.app.setActiveSubView("welcome");
                response.add("notification", "User successfully logged out");
                this.app.setServiceDeskUser(null);
            }

            default -> response.add("error", "Invalid input, please select A, B, E or X!");
        }

        return response;
    }

    private Response selectTicket(Request request) {
        Response response = new Response();

        if (request.containsUserInput() && request.get("input").toString().equals("X")) {
            this.activeSubView = "mainMenu";
            response.add("tickets", Ticket.getWhereName(this.app.getServiceDeskUser().getName()));
            return response;
        }

        if (!request.containsUserInput() || !request.isInteger()) {
            response.add("error", "Please enter a valid ticket ID as a integer");
            return response;
        }

        if (!this.app.getServiceDeskUser().validateTicketSelectionForUser(Integer.parseInt(request.get("input").toString()))) {
            response.add("error", "Ticket not found for ID '" + request.get("input").toString() + "'");
            return response;
        }

        this.app.setActiveController("ticketController");
        this.app.setActiveSubView("showTicket");
        ((TicketController) this.app.getActiveController()).selectedTicketId = Integer.parseInt(request.get("input").toString());
        request.resetUserInput();
        this.app.processInput(request);

        return response;
    }

    private Response showServiceMembers(Request request) {
        Response response = new Response();
        return response;
    }

    private Response showTicketDateRange(Request request) {
        Response response = new Response();
        String startDate = request.get("startDate").toString();
        String endDate = request.get("endDate").toString();
        response.add("startDate", startDate);
        response.add("endDate", endDate);
        try {
            Ticket.getTicketDateRange(startDate, endDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        this.app.setActiveController("ticketController");
        this.app.setActiveSubView("showTicketDateRange");
        this.app.processInput(request);

        return response;
    }


}
