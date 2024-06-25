package com.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ItemTest {

    //test item values are assigned correctly
    @Test
    public void testItemCreation(){
        Item item = new Item("1", "cabbage", 5, 0.5);

        assertNotNull(item);
        assertEquals("1", item.getId());
        assertEquals("cabbage",item.getName());
        assertEquals(5,item.getQuantity());
        assertEquals(0.5,item.getPrice(),0.01);
    }
}
