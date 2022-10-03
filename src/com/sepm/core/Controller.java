package com.sepm.core;

public abstract class Controller {
    protected TicketManager TM;
    protected View view;
    protected String activeSubView;

    public Controller (TicketManager ticketManager) {
        this.TM = ticketManager;
    }

    public abstract void updateView(Response response);
    public abstract void processInput(Request request);
}
