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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try 
        {            
            ProductService ps = new ProductService();
            products.addAll(ps.GetAllProducts());
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
    
    
    
}
