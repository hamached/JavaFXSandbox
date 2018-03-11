/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhamacher.javafxsandbox;

import com.dhamacher.javafxsandbox.model.Product;
import com.dhamacher.javafxsandbox.service.ProductService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.SwipeEvent;

/**
 * FXML Controller class
 *
 * @author dhamacher
 */
public class HomeViewController implements Initializable {

    @FXML
    private Button storageCategoryButton;
    @FXML
    private Button childrenCategoryButton;
    @FXML
    private Button livingRoomCategoryButton;
    @FXML
    private Button kitchenCategoryButton;
    @FXML
    private Button bathCategoryButton;
    @FXML
    private Button babyCategoryButton;
    @FXML
    private ListView<Product> productListView;
    @FXML
    private ImageView productImageView;
    @FXML
    private Label nameLabel;
    @FXML
    private Label categoryLabel;
    @FXML
    private Label colorLabel;
    @FXML
    private Label priceLabel;    
    
    private final ObservableList<Product> products = 
            FXCollections.observableArrayList(); 
    
    private ProductService productService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try 
        {            
            productService = new ProductService();
            products.addAll(productService.GetAllProducts());
            productListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>(){
                public void changed(ObservableValue<? extends Product> observable, Product oldProduct, Product newProduct) {
                    priceLabel.setText(String.valueOf(newProduct.getPrice()));
                    colorLabel.setText(newProduct.getColor());
                    categoryLabel.setText(newProduct.getCategory());
               }                
            });
            
            
            productListView.setItems(products);
            
        }
        catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Attention!");
            alert.setHeaderText("Exception Thrown!");
            alert.setContentText(e.getMessage().toString());
            alert.showAndWait();
        }
    }  

    @FXML
    private void CategoryClickedEvent(MouseEvent event) {
        EventType<?> type = event.getEventType();
        String typeName = type.getName();    
        try {
            Button btn = (Button)event.getSource();
            String btnText = btn.getText();
            products.clear();
            products.addAll(productService.GetAllProductsByCategory(btnText));
            productListView.refresh();            
        }
        catch(Exception e)
        {
            MessageDialog.MessagePopup("ERROR", e.getMessage());            
        }       
    }  

    @FXML
    private void GetSelectedItem(MouseEvent event) {
        
    }

    @FXML
    private void ScrollDown(SwipeEvent event) {
               
    }
}
