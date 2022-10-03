package com.sepm.controller;

import com.sepm.core.Controller;
import com.sepm.core.Request;
import com.sepm.core.Response;
import com.sepm.core.TicketManager;
import com.sepm.model.Ticket;
import com.sepm.view.UserView;

public class UserController extends Controller {
    public UserController(TicketManager ticketManager) {
        super(ticketManager);
        this.view = new UserView();
        this.activeSubView = "show tickets";
    }

    @Override
    public void updateView(Response response) {
        Request request = ((UserView) this.view).showTickets(response);
        this.TM.processInput(request);

    }

    @Override
    public void processInput(Request request) {
        Response response = new Response();

        //Add our tickets to the response
        response.add("tickets", Ticket.getAll());

        this.TM.updateView(response);
    }
}
