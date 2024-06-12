package main.java.com.inventory;

import java.util.*;


public class ItemInventory {
    private List<Item> items;
    
    public ItemInventory(){ //construct inventory
        this.items = new ArrayList<>();
    }

    public void addItem(Item item){     //add items into inventory
        items.add(item);
    }

    public void deleteItem(String id){  //delete items in inventory
        items.removeIf(item->item.getId().equals(id));
    }
    public List<Item> getItems(){   //return the list of items in inventory
        return new ArrayList<>(items);
    }
    public void updateItem(String id, Item updatedItem){    //update an item in the inventory
        for(int i = 0; i<items.size(); i++){
            if(items.get(i).getId().equals(id)){
                items.set(i,updatedItem);
                return;
            }
        }
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
