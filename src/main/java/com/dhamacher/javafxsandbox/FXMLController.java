package com.dhamacher.javafxsandbox;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class FXMLController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private AnchorPane homePane;
    @FXML
    private Button getStartedButton;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleGetStartedButtonAction(ActionEvent event) {
    }
}
