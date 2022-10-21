package com.sepm.controller;

import com.sepm.core.Controller;
import com.sepm.core.Request;
import com.sepm.core.Response;
import com.sepm.core.Application;
import com.sepm.model.ServiceDeskMember;
import com.sepm.model.StaffMember;
import com.sepm.model.Ticket;
import com.sepm.model.User;
import com.sepm.service.XMLWriterService;
import com.sepm.view.AuthenticationView;

public class AuthenticationController extends Controller {

    private String loginEmail;
    private String loginPassword;
    private String registerName;
    private String registerEmail;
    private String registerPhone;
    private String registerPassword;
    private String forgotEmail;
    private User forgotPasswordUser;


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
            case "register" -> request = ((AuthenticationView)this.view).register(response);
            case "forgotPassword" -> request = ((AuthenticationView)this.view).forgotPassword(response);
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
            case "B" -> {this.activeSubView = "register";response.add("header","Please enter your full name"); request.resetUserInput();}
            case "C" -> {this.activeSubView = "forgotPassword"; request.resetUserInput();  response.add("header", "Please enter your email");}
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
            response.add("header","Please enter your password");

        }else{
            response.add("header","Please enter your password");

            if(!request.containsUserInput()){
                response.add("error","Please enter your password");
                return response;
            }

            this.loginPassword = request.get("input").toString();

            ServiceDeskMember user = ServiceDeskMember.getServiceDeskMemberByEmail(this.loginEmail);
            // StaffMember user = StaffMember.getStaffMemberByEmail(this.loginEmail);

            if(user == null){
                response.add("error","Login Failed! Please check your details and try again!");
                response.add("header","Please enter your email address");

                this.loginEmail = null;
                this.loginPassword = null;

                this.activeSubView = "welcome";

                return response;

            }

            if(!user.getPassword().equals(this.loginPassword)){
                response.add("error","Login Failed! Please check your details and try again!");
                response.add("header","Please enter your email address");

                this.loginEmail = null;
                this.loginPassword = null;

                this.activeSubView = "welcome";


                return response;
            }

            //else if we get here then the user is valid!
            this.app.setUser(user);
            this.app.setActiveController("userController");
            response.add("notification","Welcome "+user.getName()+"!");
            response.add("tickets", Ticket.getWhereName(user.getName()));
        }


        return response;
    }

    private Response register(Request request) {

        Response response = new Response();

        if (request.containsUserInput()) {
            if (request.get("input").equals("X")) {
                this.registerEmail = null;
                this.registerPhone = null;
                this.registerPassword = null;
                this.registerName = null;
                this.activeSubView = "welcome";
                response.add("notification", "User registration cancelled!");
                return response;
            }
        }


        if (registerName == null) {
            response.add("header", "Please enter your full name");
            if (!request.containsUserInput()) {
                response.add("error", "Please enter your full name");
                return response;
            }

            this.registerName = request.get("input").toString();
            response.add("notification", "Full Name set to '" + this.registerName + "'");
            response.add("header", "Please enter your email address");

            return response;
        }

        if (registerEmail == null) {
            response.add("header", "Please enter your email address");
            response.add("notification", "full name set to '" + this.registerName + "'");

            if (!request.containsUserInput()) {
                response.add("error", "Please enter your email address");
                return response;
            }

            this.registerEmail = request.get("input").toString();
            response.add("notification", "Email set to '" + this.registerEmail + "'");
            response.add("header", "Please enter your phone number");

            return response;
        }

        if (registerPhone == null) {
            response.add("header", "Please enter your phone number");
            response.add("notification", "email set to '" + this.registerEmail + "'");

            if (!request.containsUserInput()) {
                response.add("error", "Please enter your phone number");
                return response;
            }

            this.registerPhone = request.get("input").toString();
            response.add("notification", "phone number set to '" + this.registerPhone + "'");
            response.add("header", "Please enter your password");

            return response;
        }

        if (registerPassword == null) {
            response.add("header", "Please enter your password");
            response.add("notification", "phone number set to '" + this.registerPhone + "'");

            if (!request.containsUserInput()) {
                response.add("error", "Please enter your password");
                return response;
            }
            if (request.get("input").toString().length() < 19) {
                response.add("error", "Password must be at least 20 characters long \n Please enter your password:");
                return response;
            }

            this.registerPassword = request.get("input").toString();


            User.addStaffMember(new StaffMember(String.valueOf(User.getAllStaffMembers().length),this.registerName,this.registerEmail, this.registerPhone, this.registerPassword));
            XMLWriterService.saveStaffMembersToXML();
            response.add("notification","new account created '"+this.registerEmail+"'");
            this.registerEmail = null;
            this.registerPhone = null;
            this.registerPassword = null;
            this.registerName = null;
            this.activeSubView = "welcome";

        }

        return response;

    }

    private Response forgotPassword(Request request) {
        Response response = new Response();

        //----------------------------------
        //      Check for X to cancel
        //----------------------------------

        if(request.containsUserInput() && request.get("input").equals("X")){
            this.forgotPasswordUser = null;
            this.forgotEmail = null;
            this.app.setActiveSubView("welcome");
            response.add("notification","Password reset cancelled");
            return response;
        }

        //----------------------------------
        //Check email, if not set then run cod
        //----------------------------------

        if (this.forgotEmail == null) {
            response.add("header", "Please enter your email");
            if (!request.containsUserInput()) {
                response.add("error", "Please enter your email");
                return response;
            }

            this.forgotPasswordUser = User.getUserByEmail(request.get("input").toString());

            if(this.forgotPasswordUser == null){
                response.add("error", "Invalid email, please enter a valid email");
                return response;
            }

            this.forgotEmail = request.get("input").toString();
            response.add("header","Please enter your new password");
            return response;
        }

        //If we get here then the user has been found, and we move onto password validation.

        if(!request.containsUserInput()){
            response.add("error","New password cannot be null!");
            return response;
        }

        if(request.get("input").toString().length() < 19){
            response.add("error","Error, new password must be 20 characters or longer!");
            return response;
        }

        //if we reach here then the user password has been updated, we set the save and return.

        response.add("notification","User password updated for '"+this.forgotPasswordUser.getEmail()+"'");
        this.forgotPasswordUser.setPassword(request.get("input").toString());

        XMLWriterService.saveStaffMembersToXML();
        this.forgotPasswordUser = null;
        this.forgotEmail = null;
        this.activeSubView = "welcome";


        return response;
    }
}

