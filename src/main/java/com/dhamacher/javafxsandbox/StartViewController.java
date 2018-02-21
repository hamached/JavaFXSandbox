package com.dhamacher.javafxsandbox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class StartViewController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private AnchorPane homePane;
    @FXML
    private Button getStartedButton;    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleGetStartedButtonAction(ActionEvent event) {
        try
        {           
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/HomeView.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/homeview.css");
            homePane.getChildren().setAll(root);
        }
        catch (IOException e)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Attention!");
            alert.setHeaderText("Exception Thrown!");
            alert.setContentText(e.getMessage().toString());
            alert.showAndWait();
        }
    }
}
