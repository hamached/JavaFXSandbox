/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhamacher.javafxsandbox;

import com.dhamacher.javafxsandbox.model.OrderItem;
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
<<<<<<< HEAD
<<<<<<< HEAD
import javafx.event.EventType;
=======
import javafx.event.ActionEvent;
>>>>>>> 46c6a6f10d4a698614e8dd9e36e245723e3d8c37
=======
import javafx.event.ActionEvent;
>>>>>>> 6c5f833c44746f749623e96cb7c7f6f8b2fd095b
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
<<<<<<< HEAD
import javafx.scene.control.ButtonBar;
=======
import javafx.scene.control.ComboBox;
>>>>>>> 6c5f833c44746f749623e96cb7c7f6f8b2fd095b
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
<<<<<<< HEAD
import javafx.scene.input.MouseEvent;
<<<<<<< HEAD
import javafx.scene.input.SwipeEvent;
=======
import javafx.scene.input.TouchEvent;
>>>>>>> 46c6a6f10d4a698614e8dd9e36e245723e3d8c37
=======
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
>>>>>>> 6c5f833c44746f749623e96cb7c7f6f8b2fd095b

/**
 * FXML Controller class for the HomeView.fxml which is the primary 
 * catalog view for products.
 *
 * @author dhamacher
 */
public class HomeViewController implements Initializable {
    
    /* FXML components */
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
    @FXML
    private AnchorPane homeViewAnchorPane;
    @FXML
    private Spinner<Integer> quantitySpinner;
    @FXML
    private Button exitButton;
    
    
    /* Class variables */
    private ProductService productService;    
    private final ObservableList<Product> products = 
            FXCollections.observableArrayList();    
    private final ObservableList<String> categories = 
            FXCollections.observableArrayList();     
    private List<OrderItem> currentOrder;
    
    
    /**
     * Initializes the controller and its resources.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try 
        {  
            SpinnerValueFactory<Integer> valFact = 
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
            quantitySpinner.setValueFactory(valFact);
            
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
                    //File file = new File("/images/storage.jpeg");
                    //Image image = new Image(file.toURI().toString());
                    //productImageView.setImage(image);
                    //NumberFormat formatter = NumberFormat.getCurrencyInstance();
                    quantitySpinner.getValueFactory().setValue(1);
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
            
            /* Add listerner to the price slider */
            priceSlider.valueProperty().addListener(new ChangeListener<Number>() 
            {
                public void changed(ObservableValue<? extends Number> ov,
                    Number oldValue, Number newValue) 
                {
                    sortByCatgeoryAndPrice(categoryDropDown.getValue(), priceSlider.getValue());
                }           
            });        
            
            /* Add data to containers */
            categoryDropDown.setItems(categories);            
            productListView.setItems(products);            
        }
        catch(Exception e)
        {            
<<<<<<< HEAD
            MessageDialog.MessagePopup("EXCEPTION", e.getMessage().toString());            
        }
    }    

    @FXML
    private void sortByCategory(ActionEvent event) {
         String category = null;
        try
        {
            ProductService service = new ProductService();
            Button btn = (Button) event.getSource();
            category = btn.getText();
            products.clear();
            productListView.getItems().clear();            
            products.addAll(service.GetAllProductsByCategory(btn.getText()));            
            productListView.setItems(products);
            productListView.refresh();            
        }
<<<<<<< HEAD
    }  
=======
            MessageDialog.MessagePopup(MessageDialog.Status.EXCEPTION,
                    "Exception thrown in Initialize() method. Message: " +
                            e.getMessage().toString());            
        }
    }    
>>>>>>> 6c5f833c44746f749623e96cb7c7f6f8b2fd095b

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
        } catch(Exception e) 
        {
            MessageDialog.MessagePopup(MessageDialog.Status.EXCEPTION
                    , "Exception Thrown in sortByCategory() method. Message: "
                    + e.getMessage());
        }
    } 
    
    /**
     * 
     * @param category
     * @param price 
     */
    private void sortByCatgeoryAndPrice(String category, double price)
    {
        try
        {           
            ProductService ps = new ProductService();        
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            upperPriceRange.setText(formatter.format(price));
            String cat = null; 
            upperPriceRange.setText(formatter.format(price));                            
            ps.GetAllProductsByPrice(price);
        }
        catch (Exception e)
        {
            MessageDialog.MessagePopup(MessageDialog.Status.EXCEPTION,
                    "Exception thrown in sortByCatgeoryAndPrice() method. Message: "
                        + e.getMessage());
        }
    }

    @FXML
    private void addToOrder(ActionEvent event) 
    {
        try
        {
            Product p = productListView.getSelectionModel().getSelectedItem();
            int qty = quantitySpinner.getValue();
            OrderItem item = new OrderItem();            
            item.setName(p.getName());
            item.setQuantity(qty);
            item.setPricePerItem(p.getPrice());
            item.setTotalPrice(qty * p.getPrice());
            currentOrder.add(item);
        }
        catch (Exception e)
        {
            MessageDialog.MessagePopup(MessageDialog.Status.EXCEPTION,
                    "Exception thrown in addToOrder() method. Message: "
                    + e.getMessage());
        }
    }

    @FXML
    private void viewCurrentOrder(ActionEvent event) 
    {   
        try
        {          
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/OrderView.fxml"));            
            Parent root = (Parent)loader.load(); 
            /* Get controller and set order ist */
            OrderViewController controller = loader.<OrderViewController>getController();
            controller.setOrderItemList(currentOrder);
            
            /* Load Scene */
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/orderview.css");            
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Order Overview");
            stage.setScene(scene);  
            stage.show();           
        }
        catch (Exception e)
        {
            MessageDialog.MessagePopup(MessageDialog.Status.EXCEPTION,
                    "Exception thrown in viewCurrentOrder() method. Message: "
                    + e.getMessage());
        }
    }  

    @FXML
    private void proceedToCheckout(ActionEvent event) {
        int confirmed = MessageDialog.MessagePopup(MessageDialog.Status.CONFIRMATION, 
                "Do you wish to schedule a Delivery?");
        try
        {
            if (confirmed == 1)
            {                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DeliveryView.fxml"));  
                Parent root = (Parent)loader.load();            
                Scene scene = new Scene(root);
                scene.getStylesheets().add("/styles/orderview.css");            
                Stage stage = new Stage();
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initStyle(StageStyle.UTILITY);
                stage.setTitle("Schedule a Delivery");
                stage.setScene(scene);  
                stage.show();  
            }           
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/OrderConfirmationView.fxml"));            
            Parent root = (Parent)loader.load(); 
            /* Get controller and set order ist */
            OrderConfirmationViewController controller = loader.<OrderConfirmationViewController>getController();
            controller.setOrderItemList(currentOrder);

            /* Load Scene */
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/orderconfirmationview.css");            
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Order Overview");
            stage.setScene(scene);  
            stage.show();           
        }
        catch (Exception e) 
        {
            MessageDialog.MessagePopup(MessageDialog.Status.EXCEPTION,
                    e.getMessage());
        }
    }

    @FXML
<<<<<<< HEAD
    private void ScrollDown(SwipeEvent event) {
               
=======
        catch(Exception e)
        {
            MessageDialog.MessagePopup("ERROR", "The category " + category 
                    + " does not match");
        }
>>>>>>> 46c6a6f10d4a698614e8dd9e36e245723e3d8c37
=======
    private void closeWindow(ActionEvent event) 
    {
        int status = MessageDialog.MessagePopup(MessageDialog.Status.CONFIRMATION,
                "Do you really want to cancel the order?");
        if (status == 1)
        {
            Stage stage = (Stage)exitButton.getScene().getWindow();          
            stage.close();
        }
>>>>>>> 6c5f833c44746f749623e96cb7c7f6f8b2fd095b
    }
}
