package com.inventory;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;



public class ItemInventoryTest {
    private ItemInventory inventory;

    // setup inventory with 1 item
    @BeforeEach
    public void setup(){
        inventory = new ItemInventory();
        inventory.addItem(new Item("1","Laptop",10,200.00 ));
    }

    //test success output when valid item quantity changes
    @Test
    public void testUpdateItemQuantitySuccessfully(){
        Item updatedItem = new Item("1", "Laptop", 8, 200.00);
        String result = inventory.updateItem("1", updatedItem);
        assertEquals("Item updated successfully.",result);
    }

    //test success output when valid price changes
    @Test
    public void testUpdateItemPriceSuccessfully(){
        Item updatedItem = new Item("1", "Laptop", 8, 80.00);
        String result = inventory.updateItem("1", updatedItem);
        assertEquals("Item updated successfully.",result);
    }

    //test updated item is not null
    @Test
    public void testUpdateItemNull(){
        //test updateItem throws exception when update is null
        Item updatedItem = null;
        Exception exception = assertThrows(IllegalArgumentException.class,()->{
            inventory.updateItem("1", updatedItem);
        });
        assertEquals("Updated item cannot be null.",exception.getMessage());
    }

    //test updated item is not found
    @Test
    public void testUpdateItemNotFound(){
        //test updateItem throws exception when item is not found
        Item updatedItem = new Item("2xf","laptop",4,250.00);
        Exception exception = assertThrows(IllegalArgumentException.class, ()->{
            inventory.updateItem("2xf", updatedItem);
        });
        assertEquals("Item with id " + "2xf" + " cannot be found.",exception.getMessage());
    }
    @Test
    public void testUpdateItemPrice(){
        //test updateItem throws exception when update contains negative price
        Item updatedItemPrice = new Item("1","Laptop",10,-200.00 );
        Exception exception = assertThrows(IllegalArgumentException.class, ()->{
            inventory.updateItem("1", updatedItemPrice);
        });
        assertEquals("Quantity and Price must not be less than 0.", exception.getMessage());
    } 

    @Test
    public void testUpdateItemQuantity(){
        //test updateItem throws exception when update contains negative quantity
        Item updatedItemQuantity = new Item("1","Laptop",-10,200.00 );
        Exception exception = assertThrows(IllegalArgumentException.class, ()->{
            inventory.updateItem("1", updatedItemQuantity);
        });
        assertEquals("Quantity and Price must not be less than 0.", exception.getMessage());
    } 

    //test adding items
    @Test
    public void testAddItem(){
        //adding a valid item returns a success message
        Item newItem = new Item("2", "apple", 8, 0.9);
        String result = inventory.addItem(newItem);
        assertEquals("Item added.",result);
        
        //adding empty item throws an erro
        Item emptyItem = null;
        Exception exceptionEmpty = assertThrows(IllegalArgumentException.class, ()->{
            inventory.addItem(emptyItem);
        });
        assertEquals("Item is empty.",exceptionEmpty.getMessage());

        //adding item with negative price throws error
        Item badPricItem = new Item("1", "pear", 1, -0.5);
        Exception exceptionNegativePrice = assertThrows(IllegalArgumentException.class, ()->{
            inventory.addItem(badPricItem);
        });
        assertEquals("Quantity and Price must not be less than 0.", exceptionNegativePrice.getMessage());
        
        //adding item with negative quantity throws error
        Item badQuantityItem = new Item("1", "Laptop", -1, 0.5);
        Exception exceptionNegativeQuantity = assertThrows(IllegalArgumentException.class, ()->{
            inventory.addItem(badQuantityItem);
        });
        assertEquals("Quantity and Price must not be less than 0.", exceptionNegativeQuantity.getMessage());   
    }

    //test item deleted
    @Test
    public void testDeleteItem(){
        inventory.deleteItem("1");
        assertNull(inventory.searchItem("1"));
    }
    //test item search function;
    @Test
    public void testSearchItem(){
        Item search = inventory.searchItem("1");
        assertNotNull(search);
        assertEquals("Laptop", search.getName());
        
        Item searchNull = inventory.searchItem("2");
        assertNull(searchNull);
    }



    //test inventory is returned correctly
   @Test
    public void testGetItems() {
        Item item = new Item("2", "Pizza", 2, 2.00);
        inventory.addItem(item);
        List<Item> result = inventory.getItems();
        List<Item> test = new ArrayList<>(Arrays.asList(
            new Item("1", "Laptop", 10, 200.00),
            new Item("2", "Pizza", 2, 2.00)
        ));
        assertEquals(test, result);
    }
}

