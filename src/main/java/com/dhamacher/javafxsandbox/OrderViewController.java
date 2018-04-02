/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhamacher.javafxsandbox;

import com.dhamacher.javafxsandbox.model.Product;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dhamacher
 */
public class OrderViewController implements Initializable {

    @FXML
    private Button okButton;        
    
    @FXML
    private TableView<?> orderTable;
    
    private List<Product> list;

    public OrderViewController(List<Product> list)
    {
        this.list = new ArrayList<>();
        this.list = list;        
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage)okButton.getScene().getWindow();          
        stage.close();
    }    
}
