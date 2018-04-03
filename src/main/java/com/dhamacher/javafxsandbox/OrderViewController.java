/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhamacher.javafxsandbox;

import com.dhamacher.javafxsandbox.model.OrderItem;
import com.dhamacher.javafxsandbox.model.Product;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
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
    private TableView<OrderItem> orderTable = new TableView<OrderItem>();
    @FXML
    private TableColumn<OrderItem, String> productNameColumn = new TableColumn("Product Name");
    @FXML
    private TableColumn<OrderItem, Integer> quantityColumn = new TableColumn("Quantity");
    @FXML
    private TableColumn<OrderItem, Double> priceColumn = new TableColumn("Price");
    
    private final ObservableList<OrderItem> data = 
            FXCollections.observableArrayList();
    

    public OrderViewController(List<OrderItem> list)
    {  
        this.data.addAll(list);
        //orderTable.getColumns().addAll(productNameColumn, quantityColumn, priceColumn);
        //orderTable.setItems(data);
    }
    
    public OrderViewController()
    {
        
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        orderTable.getColumns().addAll(productNameColumn, quantityColumn, priceColumn);
        orderTable.setItems(data);
    }    

    @FXML
    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage)okButton.getScene().getWindow();          
        stage.close();
    }    
}
