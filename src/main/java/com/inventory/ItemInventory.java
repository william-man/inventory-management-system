package com.inventory;

import java.io.Serializable;
import java.util.*;


public class ItemInventory implements Serializable{
    private List<Item> items;
    
    public ItemInventory(){ //construct inventory
        this.items = new ArrayList<>();
    }

    public String addItem(Item item) throws IllegalArgumentException {     //add items into inventory
        if(item == null){
            throw new IllegalArgumentException("Item is empty.");
        }
        if(item.getQuantity() < 0 || item.getPrice() < 0){
            throw new IllegalArgumentException("Quantity and Price must not be less than 0.");
        }
        items.add(item);
        return "Item added.";
    }

    public boolean deleteItem(String id){  //delete items in inventory
        boolean result = items.removeIf(item->item.getId().equals(id));
        return result;
    }

    public List<Item> getItems(){   //return the list of items in inventory
        return new ArrayList<>(items);
    }

    public String updateItem(String id, Item updatedItem) throws IllegalArgumentException {    //update an item in the inventory
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
    public Item searchItem(String id){  //search for item by id
        for(Item item : items){
            if(item.getId().equals(id)){
                return item;
            }
        }
        return null;
    }
}
