/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhamacher.javafxsandbox.model;

/**
 *
 * @author dhamacher
 */
public class OrderItem {   
    
    private String name;      
    private int quantity;
    private double price;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public OrderItem(int id, String name, int quanity, double price)
    {        
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    } 
    
    public OrderItem()
    {        
    }   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }   

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }  
    
    @Override
    public String toString()
    {
        return name;
    }    
}