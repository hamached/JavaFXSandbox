/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhamacher.javafxsandbox;

import com.dhamacher.javafxsandbox.model.Product;
import com.dhamacher.javafxsandbox.service.ProductService;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author dhamacher
 */
public class ProductController implements Initializable {
    
    final ObservableList<Product> products = FXCollections.observableArrayList();           

    @FXML
    private ListView<Product> productListView;
    @FXML
    private TextField productNameField;
    @FXML
    private TextField productCategoryField;
    @FXML
    private TextField productColorField;
    @FXML
    private TextField productPriceField;

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

    @FXML
    private void handleProductSaveButton(ActionEvent event) {
        try
        {
            ProductService ps = new ProductService();
            ps.CreateProduct(
                    productNameField.getText(), 
                    productCategoryField.getText(), 
                    Double.parseDouble(productPriceField.getText()), 
                    productColorField.getText());
            productListView.refresh();
            MessageDialog.MessagePopup("INFO", "Product successfully created.");
        }
        catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Attention!");
            alert.setHeaderText("Exepction Thrown!");
            alert.setContentText(e.getMessage().toString());
            alert.showAndWait();
        }
    }
    
    
}
