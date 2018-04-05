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
    private double pricePerItem;
    private double totalPrice;

    public double getPricePerItem() {
        return pricePerItem;
    }

    public void setPricePerItem(double pricePerItem) {
        this.pricePerItem = pricePerItem;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public OrderItem(int id, String name, int qty, double price)
    {        
        this.name = name;
        this.quantity = quantity;
        this.pricePerItem = price;
        this.totalPrice = price * qty;
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
    
    @Override
    public String toString()
    {
        return name;
    }    
}