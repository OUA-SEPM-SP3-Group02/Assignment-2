package com.sepm.controller;

import com.sepm.core.Controller;
import com.sepm.core.Request;
import com.sepm.core.Response;
import com.sepm.core.Application;
import com.sepm.model.ServiceDeskMember;
import com.sepm.view.AuthenticationView;

public class AuthenticationController extends Controller {

    private String loginEmail;
    private String loginPassword;

    public AuthenticationController(Application ticketManager) {
        super(ticketManager);
        this.view = new AuthenticationView();
        this.activeSubView = "welcome";
    }

    @Override
    public void updateView(Response response) {

        Request request = new Request();
        switch (this.activeSubView) {
            case "welcome" -> request = ((AuthenticationView)this.view).welcome(response);
            case "login" -> request = ((AuthenticationView)this.view).login(response);
            //case "register" -> request = ((AuthenticationView)this.view).register(response); -> NOT IMPLEMENTED YET
            //case "forgotPassword" -> request = ((AuthenticationView)this.view).forgotPassword(response); -> NOT IMPLEMENTED YET
        }
        this.app.processInput(request);
    }

    @Override
    public void processInput(Request request) {
        Response response = new Response();
        switch (this.activeSubView) {
            case "welcome" -> response = this.welcome(request);
            case "login" -> response = this.login(request);
            case "register" -> response = this.register(request);
            case "forgotPassword" -> response = this.forgotPassword(request);
        }
        this.app.updateView(response);
    }

    private Response welcome(Request request){

        Response response = new Response();

        if(!request.containsUserInput()){
            return response;
        }

        switch(request.get("input").toString()){
            case "A" -> {this.activeSubView = "login"; response.add("header","Please enter your email address");}
            case "B" -> this.activeSubView = "register";
            case "C" -> this.activeSubView = "forgotPassword";
            case "X" -> System.exit(0);

            default -> response.add("error","Invalid input provided!\nplease select a option from the menu");
        }

        return response;
    }

    private Response login(Request request){
        Response response = new Response();

        if(request.containsUserInput()){
            if(request.get("input").equals("X")){
                this.activeSubView = "welcome";
                response.add("notification","Login cancelled");
                return response;
            }
        }

        if(loginEmail == null){
            response.add("header","Please enter your email address");

            if(!request.containsUserInput()) {
                response.add("error", "Please enter your email");
                return response;
            }

            this.loginEmail = request.get("input").toString();
            response.add("notification","Email set to '"+this.loginEmail+"'");
            response.add("header","Please enter your password");

        }else{
            response.add("header","Please enter your password");
            response.add("notification","Email set to '"+this.loginEmail+"'");

            if(!request.containsUserInput()){
                response.add("error","Please enter your password");
                return response;
            }

            this.loginPassword = request.get("input").toString();

            ServiceDeskMember user = ServiceDeskMember.getServiceDeskMemberByEmail(this.loginEmail);

            if(user == null){
                response.add("error","Invalid email provided, please enter a valid email and try again!");
                response.add("header","Please enter your email address");

                this.loginEmail = null;
                this.loginPassword = null;

                return response;

            }

            if(!user.getPassword().equals(this.loginPassword)){
                response.add("error","Invalid email provided, please enter a valid email and try again!");
                response.add("header","Please enter your email address");

                this.loginEmail = null;
                this.loginPassword = null;

                return response;
            }

            //else if we get here then the user is valid!
            this.app.setUser(user);
            this.app.setActiveController("userController");
            response.add("notification","Welcome "+user.getName()+"!");

        }


        return response;
    }

    private Response register(Request request){

        Response response = new Response();

        response.add("error","Registration page not yet implemented, please check back soon");
        this.activeSubView = "welcome";

        return response;
    }

    private Response forgotPassword(Request request){
        Response response = new Response();

        response.add("error","Password reset not yet implemented, please check back soon");
        this.activeSubView = "welcome";

        return response;
    }
}
