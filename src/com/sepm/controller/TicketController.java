package com.sepm.controller;

import com.sepm.core.Application;
import com.sepm.core.Controller;
import com.sepm.core.Request;
import com.sepm.core.Response;
import com.sepm.model.Ticket;
import com.sepm.service.XMLWriterService;
import com.sepm.view.TicketView;

import java.util.Objects;

public class TicketController extends Controller {

    private String ticketTitle;
    private String ticketDescription;
    private String ticketLevel;
    private String ticketStatus;
    private String ticketIdTarget;

    public TicketController(Application ticketManager) {
        super(ticketManager);
        this.view = new TicketView();
        this.activeSubView = "addNewTicket";
    }

    @Override
    public void updateView(Response response) {
        Request request = new Request();
        switch (this.activeSubView) {
            case "addNewTicket" -> request = ((TicketView) this.view).addNewTicket(response);
        }
        this.app.processInput(request);
    }

    @Override
    public void processInput(Request request) {
        Response response = new Response();
        switch (this.activeSubView) {
            case "addNewTicket" -> response = this.addNewTicket(request);
        }
        this.app.updateView(response);
    }

    private Response addNewTicket(Request request) {

        //Create our new response object
        Response response = new Response();

        //|========================================================================================|
        //|                                  SET TICKET TITLE                                      |
        //|========================================================================================|

        //The add new ticket method contains a number of independent if statements that will validate
        //any user input that has been entered and set the value of that to each of our input variables.
        //We can think of the input variable null checks as a way of maintaining our program state with
        //each input being set or becoming not null, we proceed to the next step.

        //Check if the title is null, if so then we process the logic.
        if (this.ticketTitle == null) {

            //add the header to request the user ticket title input
            response.add("heading", "Please enter you ticket title: ");

            //check if request contains user input
            if (request.containsUserInput()) {

                //if the user has input then we set the ticket title to that input
                this.ticketTitle = (String) request.get("input");

                //next we then set the heading to our next question, in this case please enter your description.
                response.add("heading", "Please enter you ticket description: ");
                //next we pass the title back to the user to show its set correctly.
                response.add("ticketTitle", this.ticketTitle);

            } else {
                //else if we reach this point then the user has not input any details, and we should show an error.
                response.add("error", "Error! Please enter a title");
            }

            //finally return our response object.
            return response;

        } else {

            //lastly if the ticket is set, meaning the ticket == null fails then we simply pass the ticket title back to the user.
            response.add("ticketTitle", this.ticketTitle);
        }


        //|========================================================================================|
        //|                                 SET TICKET DESCRIPTION                                 |
        //|========================================================================================|

        //Check if our ticket description is set, if not then process this code
        if (this.ticketDescription == null) {

            //set our heading to request the ticket description.
            response.add("heading", "Please enter you ticket description: ");

            //check if the request contains user input that we can set the value of this variable
            //to.
            if (request.containsUserInput()) {

                //if so then set the value.
                this.ticketDescription = (String) request.get("input");

                //Next update our heading to request the next step, show ticket level in this case.
                response.add("heading", "Please enter you ticket level: ");

                //Next pass the ticketDescription back to the view by placing it in the response
                response.add("ticketDescription", this.ticketDescription);
            } else {

                //else if the request does not contain any user input, then we need to set the error
                //message and request the user enter a description.
                response.add("error", "Error! Please enter a description");
            }

            //if this passes, and we have set the value or set the error then we return the response.
            return response;

        } else {

            //lastly if the ticketDescription is not null, then we should pass this variable back to
            //the view by adding it to the response.
            response.add("ticketDescription", this.ticketDescription);
        }

        //|========================================================================================|
        //|                                   SET TICKET LEVEL                                     |
        //|========================================================================================|

        //Firstly check if the ticket level is null, if so then we process this code
        if (this.ticketLevel == null) {

            //set the heading to request the user enter a ticket level.
            response.add("heading", "Please enter you ticket level: ");

            //check if the request contains any user input.
            if (request.containsUserInput()) {

                //if so then set the ticket level variable to that user input.
                this.ticketLevel = (String) request.get("input");

                if(!Objects.equals(request.get("input"), "low") && !Objects.equals(request.get("input"), "medium") && !Objects.equals(request.get("input"), "high")){
                    this.ticketLevel = null;
                    response.add("error","Invalid level, please enter 'low', 'medium' or 'high' only!");
                    return response;
                }

                //next update the heading to request the next step, confirm and review in this case.
                response.add("heading", "Please review and confirm these 'Y' or 'N'");
                //next pass the ticket level back to the user via the response
                response.add("ticketLevel", this.ticketLevel);

            } else {
                //else if we reach here no user input has been entered, and we should set the error.
                response.add("error", "Error! Please enter a level");
            }

            //finally we either have the error or the level set, and we should return the response.
            return response;

        } else {

            //else if the ticket level is not null, then we have skipped the above code and simply add
            //the level
            response.add("ticketLevel", this.ticketLevel);
        }

        //|========================================================================================|
        //|                                  SET TICKET STATUS                                     |
        //|========================================================================================|

        //The set ticket status is a very simple check, tickets will always have a status of open when
        //they are new, in this case we simply check its null, then set the status to open.
        if (this.ticketStatus == null) {

            //set the ticket status
            this.ticketStatus = "open";

            //add the ticket status to our response to be displayed.
            response.add("ticketStatus", "open");
        }

        //|========================================================================================|
        //|                                  CONFIRM OR CANCEL                                     |
        //|========================================================================================|

        //next update the heading to request the next step, confirm and review in this case.
        response.add("heading", "Please review and confirm these 'Y' or 'N'");

        //Next we can say that if we have reached this stage of the method that we are ready to
        //either cancel or confirm the new ticket as all the input has been collected. Firstly
        //we check if the request contains user input.
        if (request.containsUserInput()) {

            //next we check if that input is "Y" or "N" using this switch statement and process the
            //save or cancel request accordingly.
            switch (request.get("input").toString()) {

                //Process our "Y" (Save new ticket)
                case "Y" -> {

                    //As we currently do not have time to program the medium ticket level, all
                    //medium tickets are set to low.
                    if(this.ticketLevel.equals("medium")){
                        this.ticketLevel = "low";
                    }

                    //Save the ticket using our XML writer service
                    XMLWriterService.saveTicketToXML(
                            String.valueOf(Ticket.getAll().length+1),
                            this.ticketTitle,
                            this.ticketDescription,
                            this.app.getUser().getName(),
                            this.app.getUser().getEmail(),
                            this.ticketLevel,
                            this.ticketStatus,
                            "",
                            "tickets.xml"
                    );

                    //After we have created the new ticket, set the controller back to the user controller.
                    this.app.setActiveController("userController");
                    //add the responses stating the new ticket has been successfully created.
                    response.add("notification", "New ticket created '"+this.ticketTitle+"'");

                    //finally reset all our values
                    this.ticketTitle = null;
                    this.ticketDescription = null;
                    this.ticketLevel = null;
                    this.ticketStatus = null;

                    //update the view and return the response.
                    this.app.updateView(response);
                }

                //Process our "N" (Cancel new ticket creation)
                case "N" -> {

                    //Rest all our values
                    this.ticketTitle = null;
                    this.ticketDescription = null;
                    this.ticketLevel = null;
                    this.ticketStatus = null;

                    //set the active controller back to our user controller
                    this.app.setActiveController("userController");

                    //add our error to state the ticket creation was cancelled.
                    response.add("error", "New ticket creation cancelled");

                    //call our update view method and return the response.
                    this.app.updateView(response);
                }

                //else if the default runs then the user has input the wrong value, and we should return the
                //following error message.
                default -> response.add("error", "Invalid input!! Please input Y or N to continue: ");
            }
        }

        //finally return any response left at this stage.
        return response;
    }

    private Response changeTicketLevel(Request request) {
        Response response = new Response();

        if (this.ticketIdTarget == null) {
            response.add("heading", "Please enter the ticket ID you need to change: ");
            if (request.containsUserInput()) {
                this.ticketIdTarget = (String) request.get("input");
                response.add("ticketIdTarget", this.ticketIdTarget);
            } else {
                response.add("error", "Error! Please enter a title");
            }
            return response;
        } else {
            response.add("ticketIdTarget", this.ticketIdTarget);
        }
        return response;
    }


}
