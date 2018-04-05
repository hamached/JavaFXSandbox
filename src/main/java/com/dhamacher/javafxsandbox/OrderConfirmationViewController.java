/*
 * Copyright (C) 2018 Your Organisation
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.dhamacher.javafxsandbox;

import com.dhamacher.javafxsandbox.model.OrderItem;
import java.net.URL;
import java.text.NumberFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class OrderConfirmationViewController implements Initializable {
    
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
    
    private boolean scheduleDelivery = false;
    @FXML
    private Button paymentButton;
    
    
    
    
    public OrderConfirmationViewController() {  }
    
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
    
   
    public void setScheduleDelivery(boolean val)
    {
        this.scheduleDelivery = val;
    }
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        pricePerItemColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerItem"));
        pricePerItemColumn.setCellFactory(getCustomCellFactory(Double.NaN));
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        totalPriceColumn.setCellFactory(getCustomCellFactory(Double.NaN));
        
        orderTable.getColumns().add(productNameColumn);
        orderTable.getColumns().add(quantityColumn);
        orderTable.getColumns().add(pricePerItemColumn);
        orderTable.getColumns().add(totalPriceColumn);
    } 
    
    private Callback<TableColumn<OrderItem, Double>, TableCell<OrderItem, Double>> getCustomCellFactory(final Double color) {
        return (TableColumn<OrderItem, Double> param) -> {
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
