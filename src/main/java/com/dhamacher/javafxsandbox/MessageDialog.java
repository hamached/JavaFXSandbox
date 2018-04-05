/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhamacher.javafxsandbox;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * This class is used to send messages to the user on a variety of system events
 * such as errors, exceptions, warnings, and information messages.
 * @author dhamacher
 */
public class MessageDialog {
    
    public static enum Status
    {
        INFO,
        WARNING,
        ERROR,
        EXCEPTION,
        CONFIRMATION
    }
    
    public static int MessagePopup(Status type, String message)
    {
        int status = 0;
        switch(type)
        {
            case ERROR: 
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("An Error occured.");
                alert.setContentText(message);
                alert.showAndWait();
                break;
            }        
            case INFO:
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information!");
                alert.setHeaderText("Info:");
                alert.setContentText(message);
                alert.showAndWait();
                break;
            }
            case WARNING:
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Exception!");
                alert.setHeaderText("Details:");
                alert.setContentText(message);
                alert.showAndWait();
            }
            case CONFIRMATION:
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                //alert.setTitle("Please confirm");                
                //alert.setHeaderText("Look, a Confirmation Dialog");
                alert.setContentText(message);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    status = 1;
                } 
                break;
            }
        }
        return status;
    }   
}
