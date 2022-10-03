package com.sepm.controller;

import com.sepm.core.Controller;
import com.sepm.core.Request;
import com.sepm.core.Response;
import com.sepm.core.TicketManager;
import com.sepm.service.TicketService;
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
        System.out.println(TicketService.getTickets().toArray().length);
        response.add("tickets", TicketService.getTickets());
        this.TM.updateView(response);
    }
}
