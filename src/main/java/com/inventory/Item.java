package main.java.com.inventory;

/**
 * Item
 */
public class Item {

    private String id;
    private String name;
    private int quantity;
    private double price;

    public Item(String id, String name, int quantity, double price, String type){
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

    public String toString(){
        return "Item{id= " + id + ", name= " + name + ", quantity= " + quantity + ", price= " + price + "}";
    }
}