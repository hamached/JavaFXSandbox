/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhamacher.javafxsandbox.service;

import com.dhamacher.javafxsandbox.MessageDialog;
import com.dhamacher.javafxsandbox.model.Product;
import java.sql.SQLException;
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
    
    
    public ProductService()
    {
        
    }
    
    public void CreateProduct(String name,
                        String category,
                        double price,
                        String color)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.dhamacher_JavaFXSandbox");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setColor(color);
        product.setPrice(price);
        
        em.persist(product);
        em.getTransaction().commit();
        
        em.close();
        emf.close();        
    }
    
    public List<Product> GetAllProducts()
    {
        List<Product> list = new ArrayList<Product>();
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
            MessageDialog.MessagePopup("ERROR", ex.getMessage());
        }       
        return list;
    }

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
}
