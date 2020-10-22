package edu.co.icesi.controller;

import edu.co.icesi.model.Hollywood;
import edu.co.icesi.view.RegisterWindow;

public class RegisterWindowController {

    private RegisterWindow view;
    private Hollywood model;

    public RegisterWindowController(RegisterWindow view, Hollywood model){
        this.view = view;
        this.model = model;
    }
}
