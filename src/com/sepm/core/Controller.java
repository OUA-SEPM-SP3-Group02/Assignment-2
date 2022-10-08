package com.sepm.core;

public abstract class Controller {
    protected Application app;
    protected View view;
    protected String activeSubView;

    public Controller (Application ticketManager) {
        this.app = ticketManager;
    }

    public abstract void updateView(Response response);
    public abstract void processInput(Request request);

    public void setActiveSubView(String view){
        this.activeSubView = view;
    }
}
