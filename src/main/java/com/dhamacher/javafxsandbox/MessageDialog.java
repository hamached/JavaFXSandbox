/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhamacher.javafxsandbox;

import javafx.scene.control.Alert;

/**
 *
 * @author dhamacher
 */
public class MessageDialog {
    
    public static void MessagePopup(String type, String message)
    {
        if (type == "ERROR")
        {                  
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Attention!");
            alert.setHeaderText("Exception Thrown!");
            alert.setContentText(message);
            alert.showAndWait();
        }
        else if (type == "INFO")
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information!");
            alert.setHeaderText("Info:");
            alert.setContentText(message);
            alert.showAndWait();
        }
    }
    
    
}