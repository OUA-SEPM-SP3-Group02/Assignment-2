package com.sepm.controller;

import com.sepm.core.Controller;
import com.sepm.core.Request;
import com.sepm.core.Response;
import com.sepm.core.TicketManager;
import com.sepm.view.AuthenticationView;

public class AuthenticationController extends Controller {

    public AuthenticationController(TicketManager ticketManager) {
        super(ticketManager);
        this.view = new AuthenticationView();
        this.activeSubView = "welcome";
    }

    @Override
    public void updateView(Response response) {

    }

    @Override
    public void processInput(Request request) {

    }
}
