/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhamacher.javafxsandbox.service;

import com.dhamacher.javafxsandbox.MessageDialog;
import com.dhamacher.javafxsandbox.model.Product;
<<<<<<< HEAD
import java.sql.SQLException;
=======
>>>>>>> 6c5f833c44746f749623e96cb7c7f6f8b2fd095b
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author dhamacher
 */
public class ProductService { 
    
    /**
     * 
     */
    public ProductService()
    {
        
    }   
    
    
    /**
     * 
     * @return 
     */    
    public List<Product> GetAllProducts()
    {
<<<<<<< HEAD
        List<Product> list = new ArrayList<Product>();
=======
        List<Product> list = new ArrayList<>();
>>>>>>> 6c5f833c44746f749623e96cb7c7f6f8b2fd095b
        try
        {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.dhamacher_JavaFXSandbox");
            EntityManager em = emf.createEntityManager();
            Query q = em.createNamedQuery("GetAllProducts");

            list = q.getResultList(); 
            
            em.close();
            emf.close(); 
        }
        catch(Exception ex)
        {
<<<<<<< HEAD
            MessageDialog.MessagePopup("ERROR", ex.getMessage());
=======
            MessageDialog.MessagePopup(MessageDialog.Status.EXCEPTION, ex.getMessage());
>>>>>>> 6c5f833c44746f749623e96cb7c7f6f8b2fd095b
        }       
        return list;
    }

    /**
     * 
     * @param category
     * @return 
     */
    public List<Product> GetAllProductsByCategory(String category)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.dhamacher_JavaFXSandbox");
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("GetAllProductsByCategory");
        q.setParameter("category", category);
        
        List<Product> list = q.getResultList();       
        
        em.close();
        emf.close();   
        
        return list;        
    }
    
    /**
     * 
     * @return 
     */
    public List<String> GetCategories()
    {
        List<String> list = new ArrayList<>();
        try
        {
            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory("com.dhamacher_JavaFXSandbox");
            EntityManager em = emf.createEntityManager();        
            Query q = em.createNamedQuery("GetCategories");           
        
            list = q.getResultList();             
             
            em.close();
            emf.close();              
        }
        catch (Exception e) {}
        
        return list;        
    }
    
    public List<Product> GetAllProductsByCategoryAndPrice(String category, 
            double price)
    {
        List<Product> list = new ArrayList<>();
        try
        {
            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory("com.dhamacher_JavaFXSandbox");
            EntityManager em = emf.createEntityManager();        
            Query q = em.createNamedQuery("GetAllProductsByCategoryAndPrice");  
            
            q.setParameter("category", category);
            q.setParameter("price", price);
        
            list = q.getResultList();             
             
            em.close();
            emf.close(); 
            
        }
        catch (Exception e) {}
        
        return list;
    }
    
    public List<Product> GetAllProductsByPrice(double price)
    {
        List<Product> list = new ArrayList<>();
        try
        {
            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory("com.dhamacher_JavaFXSandbox");
            EntityManager em = emf.createEntityManager();        
            Query q = em.createNamedQuery("GetAllProductsByPrice");  
            
            
            q.setParameter("price", price);
        
            list = q.getResultList();             
             
            em.close();
            emf.close(); 
            
        }
        catch (Exception e) {}
        
        return list;
    }
}
