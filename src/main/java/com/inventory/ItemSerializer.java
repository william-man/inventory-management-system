package main.java.com.inventory;

import  java.io.*;
import java.util.*;

public class ItemSerializer {
    
    public static void saveItemsToFile(List<Item> items, String filename){
        ObjectOutputStream out = null;
        try{
            FileOutputStream fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(items);
        } catch (IOException e){
            System.out.println("Couldn't write to item list: " + e.getMessage());
        } finally {
            try{
                out.close();
            } catch (Exception e){
                System.out.println("Couldn't close write: " + e.getMessage());
            }
        }
    }

    public static List<Item> loadItemsFromFile(String filename){
        ObjectInputStream in = null;
        try{
            FileInputStream fis = new FileInputStream(filename);
            in = new ObjectInputStream(fis);
            return (List<Item>) in.readObject();
        } catch (IOException e){
             System.out.println("Failed to read item list: " + e.getMessage());
        } finally {
            try{
                in.close();
            } catch (Exception e){
                 System.out.println("Couldn't close read: " + e.getMessage());
            }
        }
    }
}
