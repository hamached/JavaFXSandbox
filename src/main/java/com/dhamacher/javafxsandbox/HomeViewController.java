/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhamacher.javafxsandbox;

import com.dhamacher.javafxsandbox.model.Product;
import com.dhamacher.javafxsandbox.service.ProductService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author dhamacher
 */
public class HomeViewController implements Initializable {

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
    @FXML
    private ComboBox<String> categoryDropDown;
    @FXML
    private Slider priceSlider;
    @FXML
    private Label upperPriceRange;
    
    private ProductService productService;    
    
    private final ObservableList<Product> products = 
            FXCollections.observableArrayList(); 
    
    private final ObservableList<String> categories = 
            FXCollections.observableArrayList(); 
    
    private List<Product> currentOrder;
    @FXML
    private AnchorPane homeViewAnchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try 
        {  
            Stage stg = (Stage)homeViewAnchorPane.getScene().getWindow();
            stg.resizableProperty().setValue(Boolean.FALSE);
            currentOrder = new ArrayList<>();
            productService = new ProductService();            
            products.clear();
            categories.clear();
            
            /* Populate list of products and add listener */
            products.addAll(productService.GetAllProducts());
            productListView.getSelectionModel().selectedItemProperty()
                    .addListener(new ChangeListener<Product>()
            {
                public void changed(ObservableValue<? extends Product> observable, Product oldProduct, Product newProduct) 
                {
                    File file = new File("/images/storage.jpeg");
                    Image image = new Image(file.toURI().toString());
                    productImageView.setImage(image);
                    //NumberFormat formatter = NumberFormat.getCurrencyInstance();
                    priceLabel.setText(String.valueOf(newProduct.getPrice()));
                    colorLabel.setText(newProduct.getColor());
                    categoryLabel.setText(newProduct.getCategory());
               }                
            });

            /* Populate list for drop down and add listener */
            categories.addAll(productService.GetCategories()); 
            categoryDropDown.getSelectionModel().selectedItemProperty()
                    .addListener(new ChangeListener<String>(){
                public void changed(ObservableValue<?extends String> observable, 
                        String oldCategory, String newCategory) {
                    sortByCategory(newCategory);
                }
            });  
                        
            priceSlider.valueProperty().addListener(new ChangeListener<Number>() 
            {
                public void changed(ObservableValue<? extends Number> ov,
                    Number oldValue, Number newValue) 
                {
                    sortByCatgeoryAndPrice(categoryDropDown.getValue(), priceSlider.getValue());
                }           
            });        
            
            categoryDropDown.setItems(categories);            
            productListView.setItems(products);            
        }
        catch(Exception e)
        {            
            MessageDialog.MessagePopup("EXCEPTION", e.getMessage().toString());            
        }
    }    

    /**
     * 
     * @param category 
     */
    private void sortByCategory(String category) 
    {        
        try
        {
            ProductService service = new ProductService();           
            products.clear();
            productListView.getItems().clear();            
            products.addAll(service.GetAllProductsByCategory(category));            
            productListView.setItems(products);
            productListView.refresh();            
        } catch(Exception e) {}
    } 
    
    /**
     * 
     * @param category
     * @param price 
     */
    private void sortByCatgeoryAndPrice(String category, double price)
    {
        ProductService ps = new ProductService();        
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        upperPriceRange.setText(formatter.format(price));
        String cat = null; //categoryDropDown.getValue();
//                    if(cat.equals("Select Category...") && price > 0)
//                    {

        upperPriceRange.setText(formatter.format(price));                            
        ps.GetAllProductsByPrice(price);
//                    }
//                    else if (!cat.equals("Select Category...") && price > 0)
//                    {                        
//                        upperPriceRange.setText(formatter.format(price));                            
//                        ps.GetAllProductsByCategoryAndPrice(cat, price);                            
//                    }
//                    else
//                    {
//                        upperPriceRange.setText(formatter.format(price));                            
//                    }
    }

    @FXML
    private void addToOrder(ActionEvent event) {
        Product p = productListView.getSelectionModel().getSelectedItem();
        currentOrder.add(p);
    }

    @FXML
    private void viewCurrentOrder(ActionEvent event) 
    {   
        try
        {  
            //homeViewAnchorPane.opacityProperty().setValue(0.5);
            AnchorPane root = new AnchorPane();
            OrderViewController controller = new OrderViewController(currentOrder);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/OrderView.fxml"));
                        
            //loader.setRoot(root);
            loader.setController(controller);
            loader.load();
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/orderview.css");
            
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Order Overview");
            stage.setScene(scene);  
            stage.show();            
            //homeViewAnchorPane.opacityProperty().setValue(1);
        }
        catch (IOException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Attention!");
            alert.setHeaderText("Exception Thrown!");
            alert.setContentText(e.getMessage().toString());
            alert.showAndWait();
        }
    }  
}
