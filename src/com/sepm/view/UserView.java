package com.sepm.view;

import com.sepm.core.Request;
import com.sepm.core.Response;
import com.sepm.core.View;
import com.sepm.model.Ticket;

import java.util.ArrayList;

public class UserView extends View {

    public Request showTickets(Response response) {
        System.out.println("Show tickets.");
        Request request = new Request();
        request.add("input", this.getUserInput());

        ArrayList<Ticket> tickets = (ArrayList<Ticket>) response.get("tickets");
        tickets.forEach(ticket -> {
            System.out.println(ticket.toString());
        });

        if (response.contains("error")) {
            System.out.println(response.get("error"));
        }
        return request;
    }
}
