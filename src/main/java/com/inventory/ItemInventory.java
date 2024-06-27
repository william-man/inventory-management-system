package com.inventory;

import java.io.Serializable;
import java.util.*;

/**
 * ItemInventory
 */

public class ItemInventory implements Serializable{
    private List<Item> items;
    
    //construct inventory
    public ItemInventory(){ 
        this.items = new ArrayList<>();
    }
    //add items into inventory
    public String addItem(Item item) throws IllegalArgumentException {     
        if(item == null){
            throw new IllegalArgumentException("Item is empty.");
        }
        if(item.getQuantity() < 0 || item.getPrice() < 0){
            throw new IllegalArgumentException("Quantity and Price must not be less than 0.");
        }
        items.add(item);
        return "Item added.";
    }

    //delete items in inventory
    public boolean deleteItem(String id){  
        boolean result = items.removeIf(item->item.getId().equals(id));
        return result;
    }

    //return the list of items in inventory
    public List<Item> getItems(){   
        return new ArrayList<>(items);
    }

    //update an item in the inventory
    public String updateItem(String id, Item updatedItem) throws IllegalArgumentException {    
        if(updatedItem == null){
            throw new IllegalArgumentException("Updated item cannot be null.");
        }
        if(updatedItem.getQuantity() < 0 || updatedItem.getPrice() < 0){
            throw new IllegalArgumentException("Quantity and Price must not be less than 0.");
        }
        for(int i = 0; i<items.size(); i++){
            if(items.get(i).getId().equals(id)){
                items.set(i,updatedItem);
                return "Item updated successfully.";
            }
        }
        throw new IllegalArgumentException("Item with id " + id + " cannot be found.");
    }

    //search for item by id
    public Item searchItem(String id){  
        for(Item item : items){
            if(item.getId().equals(id)){
                return item;
            }
        }
        return null;
    }
}
