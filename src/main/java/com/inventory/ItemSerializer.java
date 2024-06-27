package com.inventory;

import  java.io.*;
import java.util.*;

/**
 * ItemSerializer
 */


public class ItemSerializer {
    // save inventory items into serialized file
    public static void saveItemsToFile(List<Item> items, String filename) throws IOException{
        ObjectOutputStream out = null;
        try{
            FileOutputStream fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(items);
        } finally {
            if(out != null){
                out.close();
            }
        }
    }
    // load inventory items from serialized file
    public static ArrayList<Item> loadItemsFromFile(String filename) throws IOException, ClassNotFoundException{
        ObjectInputStream in = null;
        ArrayList<Item> items = null;
        try{
            FileInputStream fis = new FileInputStream(filename);
            in = new ObjectInputStream(fis);
            items = (ArrayList<Item>) in.readObject();
        } finally {
            if(in != null){
                in.close();
            }
        }
        return items;
    }
}
