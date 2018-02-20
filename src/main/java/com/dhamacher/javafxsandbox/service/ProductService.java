/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhamacher.javafxsandbox.service;

import com.dhamacher.javafxsandbox.model.Product;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author dhamacher
 */
public class ProductService {
    
    private EntityManager em;
    private EntityManagerFactory emf;
    
    public ProductService()
    {
        
    }
    
    public void CreateProduct(String name,
                        String category,
                        double price,
                        String color)
    {
        this.emf = Persistence.createEntityManagerFactory("com.dhamacher_JavaFXSandbox_jar_1.0PU");
        this.em = emf.createEntityManager();
        
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
    
}
