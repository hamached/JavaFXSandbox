/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhamacher.javafxsandbox;

import com.dhamacher.javafxsandbox.model.OrderItem;
import com.dhamacher.javafxsandbox.model.Product;

import java.net.URL;
import java.text.NumberFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;


/**
 * FXML Controller class
 *
 * @author dhamacher
 */
public class OrderViewController implements Initializable {

    @FXML
    private Button okButton;
    @FXML
    private Label subtotalLabel;
    @FXML
    private Label gstLabel;
    @FXML
    private Label totalLabel;      
    @FXML
    private TableView<OrderItem> orderTable = new TableView<>();
    @FXML
    private TableColumn<OrderItem, String> productNameColumn = new TableColumn<>("Product Name");
    @FXML
    private TableColumn<OrderItem, Integer> quantityColumn = new TableColumn<>("Quantity");
    @FXML
    private TableColumn<OrderItem, Double> pricePerItemColumn;
    @FXML
    private TableColumn<OrderItem, Double> totalPriceColumn;
    
    private final ObservableList<OrderItem> data = 
            FXCollections.observableArrayList();
    
    
    
    
    public OrderViewController() {  }
    
    /**
     * 
     * @param list 
     */
    public void setOrderItemList(List<OrderItem> list)
    {
        double subTotal = 0;
        double gstPercentage = 0.05;
        double salesTax = 0;
        double totalPrice = 0;
        NumberFormat formatter = NumberFormat.getCurrencyInstance();      
        
        for(OrderItem item : list)
        {
            subTotal += item.getTotalPrice(); 
        }
        salesTax = subTotal * gstPercentage;
        totalPrice = subTotal + salesTax;
        subtotalLabel.setText(formatter.format(subTotal));
        gstLabel.setText(formatter.format(salesTax));
        totalLabel.setText(formatter.format(totalPrice));
        this.data.addAll(list);         
        orderTable.setItems(data);
   }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        productNameColumn.setCellValueFactory(new PropertyValueFactory<OrderItem, String>("name"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<OrderItem, Integer>("quantity"));
        pricePerItemColumn.setCellValueFactory(new PropertyValueFactory<OrderItem, Double>("pricePerItem"));
        pricePerItemColumn.setCellFactory(getCustomCellFactory(Double.NaN));
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<OrderItem, Double>("totalPrice"));
        totalPriceColumn.setCellFactory(getCustomCellFactory(Double.NaN));
        
        orderTable.getColumns().add(productNameColumn);
        orderTable.getColumns().add(quantityColumn);
        orderTable.getColumns().add(pricePerItemColumn);
        orderTable.getColumns().add(totalPriceColumn);
    } 
    
    private Callback<TableColumn<OrderItem, Double>, TableCell<OrderItem, Double>> getCustomCellFactory(final Double color) {
        return new Callback<TableColumn<OrderItem, Double>, TableCell<OrderItem, Double>>() {

            @Override
            public TableCell<OrderItem, Double> call(TableColumn<OrderItem, Double> param) {
                TableCell<OrderItem, Double> cell = new TableCell<OrderItem, Double>() {
                    
                    private NumberFormat formatter = NumberFormat.getCurrencyInstance();                    
                    @Override
                    public void updateItem(final Double item, boolean empty) {
                        if (item != null) {
                            setText(formatter.format(item));                            
                        }
                    }
                };
                return cell;
            }
        };
    }   
    

    /**
     * 
     * @param event 
     */
    @FXML
    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage)okButton.getScene().getWindow();          
        stage.close();
    }    
}
