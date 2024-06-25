package com.inventory;

import java.io.Serializable;
import java.util.Objects;

/**
 * Item
 */
public class Item implements Serializable {

    private String id;
    private String name;
    private int quantity;
    private double price;

    public Item(String id, String name, int quantity, double price){
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getQuantity(){
        return quantity;
    }
    public double getPrice(){
        return price;
    }
    // Overide toString method
    public String toString(){
        return "Item{id= " + id + ", name= " + name + ", quantity= " + quantity + ", price= " + price + "}";
    }

    // Overide equals method
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass())  return false;
        Item item = (Item) o;
        return quantity == item.quantity && Double.compare(item.price, price) == 0 && Objects.equals(id, item.id) && Objects.equals(name, item.name);
    }

    //Overide hashcode method
    public int hashcode(){
        return Objects.hash(id, name, quantity, price);
    }
}