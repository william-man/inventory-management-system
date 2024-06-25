package com.inventory;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class InventoryGUI {
    private JFrame frame;
    private JPanel inventoryPanel;
    private JPanel controlPanel;
    private JPanel addItemPanel;
    private JPanel deleteItemPanel;
    private JPanel updateItemPanel;
    private JPanel searchItemPanel;
    private JPanel cards;
    private JTable inventoryTable;
    private DefaultTableModel tableModel;
    private ItemInventory inventory;

    private Stack<String> panelHistory;
    private static final String CONTROL_PANEL = "Control Panel";
    private static final String ADD_ITEM_PANEL = "Add Item Panel";
    private static final String DELETE_ITEM_PANEL = "Delete Item Panel";
    private static final String UPDATE_ITEM_PANEL = "Update Item Panel";
    private static final String SEARCH_ITEM_PANEL = "Search Item Panel";
    
    

    public InventoryGUI(){
        inventory = new ItemInventory();
        panelHistory = new Stack<>();
        initialize();
    }

    private void initialize(){
        frame = new JFrame("Inventory Management");
        frame.setBounds(100,100,1200,900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        /////////////////////////////////////////////////
        // Inventory Panel
        /////////////////////////////////////////////////

        inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new BorderLayout());

        // Table model and column names

        String[] columnNames = {"ID", "Name", "Quantity", "Price"};
        tableModel = new DefaultTableModel(columnNames,0);

        inventoryTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(inventoryTable);
        inventoryPanel.add(scrollPane, BorderLayout.CENTER);

        // centre align text in cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0; i<inventoryTable.getColumnCount(); i++){
            inventoryTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        /////////////////////////////////////////////////
        // Control Panel
        /////////////////////////////////////////////////

        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(10,1,5,5));

        JButton btnAdd = new JButton("Add Item");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                showAddItemPanel();
            }
        });
        JButton btnDelete = new JButton("Delete Item");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                showDeleteItemPanel();
            }
        });
        JButton btnUpdate = new JButton("Update Item");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                showUpdateItemPanel();
            }
        });
        JButton btnSearch = new JButton("Search Item");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                showSearchItemPanel();
            }
        });
        JButton btnShowAll = new JButton("Show All");
        btnShowAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                updateTable();
            }
        });

        JButton btnSave = new JButton("Save Inventory");
        btnSave.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    saveInventory();
                }
            }
        );

        JButton btnLoad = new JButton("Load Inventory");
        btnLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                loadInventory();
            }
        });
        

        controlPanel.add(btnAdd);
        controlPanel.add(btnDelete);
        controlPanel.add(btnUpdate);
        controlPanel.add(btnSearch);
        controlPanel.add(btnShowAll);
        controlPanel.add(btnSave);
        controlPanel.add(btnLoad);

        /////////////////////////////////////////////////
        // Card Panel
        /////////////////////////////////////////////////

        cards = new JPanel(new CardLayout());

        /////////////////////////////////////////////////
        // Add Item Panel
        /////////////////////////////////////////////////

        addItemPanel = new JPanel(new GridBagLayout());
        GridBagConstraints addBagConstraints = new GridBagConstraints();
        addBagConstraints.insets = new Insets(5, 10, 5, 10);
        addBagConstraints.fill = GridBagConstraints.HORIZONTAL;
       

        JLabel labelID = new JLabel("ID:");
        addBagConstraints.weightx = 1;
        addBagConstraints.gridx = 0;
        addBagConstraints.gridy = 0;
        addItemPanel.add(labelID,addBagConstraints);
        JTextField fieldID = new JTextField();
        fieldID.setPreferredSize(new Dimension(250,25));
        addBagConstraints.gridx = 1;
        addBagConstraints.gridy = 0;
        addItemPanel.add(fieldID,addBagConstraints);

        JLabel labelName = new JLabel("Name:");
        addBagConstraints.gridx = 0;
        addBagConstraints.gridy = 1;
        addItemPanel.add(labelName,addBagConstraints);
        JTextField fieldName = new JTextField();
        fieldName.setPreferredSize(new Dimension(250,25));
        addBagConstraints.gridx = 1;
        addBagConstraints.gridy = 1;
        addItemPanel.add(fieldName,addBagConstraints);

        JLabel labelQuantity = new JLabel("Quantity:");
        addBagConstraints.gridx = 0;
        addBagConstraints.gridy = 2;
        addItemPanel.add(labelQuantity,addBagConstraints);
        JTextField fieldQuantity = new JTextField();
        fieldQuantity.setPreferredSize(new Dimension(250,25));
        addBagConstraints.gridx = 1;
        addBagConstraints.gridy = 2;
        addItemPanel.add(fieldQuantity,addBagConstraints);

        JLabel labelPrice = new JLabel("Price:");
        addBagConstraints.gridx = 0;
        addBagConstraints.gridy = 3;
        addItemPanel.add(labelPrice,addBagConstraints);
        JTextField fieldPrice = new JTextField();
        fieldPrice.setPreferredSize(new Dimension(250,25));
        addBagConstraints.gridx = 1;
        addBagConstraints.gridy = 3;
        addItemPanel.add(fieldPrice,addBagConstraints);

        JButton btnSubmit = new JButton("Submit");
        addBagConstraints.gridx = 0;
        addBagConstraints.gridy = 4;
        addBagConstraints.gridwidth = 2;
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                addItem(fieldID.getText(),fieldName.getText(),fieldQuantity.getText(),fieldPrice.getText());
                fieldID.setText("");
                fieldName.setText("");
                fieldQuantity.setText("");
                fieldPrice.setText("");
            }
        });
        addItemPanel.add(btnSubmit,addBagConstraints);


        JButton btnBackAdd = new JButton("Back");
        addBagConstraints.gridx = 0;
        addBagConstraints.gridy = 5;
        addBagConstraints.gridwidth = 2;
        btnBackAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                showPreviousPanel();
            }
        });
        addItemPanel.add(btnBackAdd,addBagConstraints);
        

        /////////////////////////////////////////////////
        // Delete Item Panel
        /////////////////////////////////////////////////

        deleteItemPanel = new JPanel(new GridBagLayout());
        GridBagConstraints deleteBagConstraints = new GridBagConstraints();
        deleteBagConstraints.insets = new Insets(5, 10,5,10);
        deleteBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelDeleteID = new JLabel("ID:");
        deleteBagConstraints.weightx = 1;
        deleteBagConstraints.gridx = 0;
        deleteBagConstraints.gridy = 0;
        deleteItemPanel.add(labelDeleteID,deleteBagConstraints);
        JTextField fieldDeleteID = new JTextField();
        fieldDeleteID.setPreferredSize(new Dimension(250,25));
        deleteBagConstraints.gridx = 1;
        deleteBagConstraints.gridy = 0;
        deleteItemPanel.add(fieldDeleteID,deleteBagConstraints);

        JButton btnDeleteSubmit = new JButton("Delete");
        deleteBagConstraints.gridx = 0;
        deleteBagConstraints.gridy = 2;
        deleteBagConstraints.gridwidth = 2;
        btnDeleteSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String deleteId = fieldDeleteID.getText();
                deleteItem(deleteId);
                fieldDeleteID.setText("");
            }
        });
        deleteItemPanel.add(btnDeleteSubmit,deleteBagConstraints);

        JButton btnBackDelete = new JButton("Back");
        deleteBagConstraints.gridx = 0;
        deleteBagConstraints.gridy = 3;
        deleteBagConstraints.gridwidth = 2;
        btnBackDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                showPreviousPanel();
            }
        });
        deleteItemPanel.add(btnBackDelete,deleteBagConstraints);

        /////////////////////////////////////////////////
        // Update Item Panel
        /////////////////////////////////////////////////

        updateItemPanel = new JPanel(new GridBagLayout());
        GridBagConstraints updateBagConstraints = new GridBagConstraints();
        updateBagConstraints.insets = new Insets(5, 10, 5, 10);
        updateBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelUpdatedItemId = new JLabel("ID:");
        updateBagConstraints.weightx = 1;
        updateBagConstraints.gridx = 0;
        updateBagConstraints.gridy = 0;
        updateItemPanel.add(labelUpdatedItemId,updateBagConstraints);
        JTextField fieldUpdateId = new JTextField();
        fieldUpdateId.setPreferredSize(new Dimension(250,25));
        updateBagConstraints.gridx = 1;
        updateBagConstraints.gridy = 0;
        updateItemPanel.add(fieldUpdateId,updateBagConstraints);

        JLabel labelUpdatedItemName = new JLabel("Name:");
        updateBagConstraints.gridx = 0;
        updateBagConstraints.gridy = 1;
        updateItemPanel.add(labelUpdatedItemName,updateBagConstraints);
        JTextField fieldUpdateName = new JTextField();
        fieldUpdateName.setPreferredSize(new Dimension(250,25));
        updateBagConstraints.gridx = 1;
        updateBagConstraints.gridy = 1;
        updateItemPanel.add(fieldUpdateName,updateBagConstraints);

        JLabel labelUpdatedItemQuantity = new JLabel("Quantity:");
        updateBagConstraints.gridx = 0;
        updateBagConstraints.gridy = 2;
        updateItemPanel.add(labelUpdatedItemQuantity,updateBagConstraints);
        JTextField fieldUpdateQuantity = new JTextField();
        fieldUpdateName.setPreferredSize(new Dimension(250,25));
        updateBagConstraints.gridx = 1;
        updateBagConstraints.gridy = 2;
        updateItemPanel.add(fieldUpdateQuantity,updateBagConstraints);

        JLabel labelUpdatedItemPrice = new JLabel("Price:");
        updateBagConstraints.gridx = 0;
        updateBagConstraints.gridy = 3;
        updateItemPanel.add(labelUpdatedItemPrice,updateBagConstraints);
        JTextField fieldUpdatePrice = new JTextField();
        fieldUpdatePrice.setPreferredSize(new Dimension(250,25));
        updateBagConstraints.gridx = 1;
        updateBagConstraints.gridy = 3;
        updateItemPanel.add(fieldUpdatePrice,updateBagConstraints);

        JButton btnUpdateSubmit = new JButton("Submit");
        updateBagConstraints.gridx = 0;
        updateBagConstraints.gridy = 4;
        updateBagConstraints.gridwidth = 2;
        btnUpdateSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(fieldUpdateId.getText().isEmpty() || fieldUpdateName.getText().isEmpty() || fieldUpdateQuantity.getText().isEmpty() || fieldUpdatePrice.getText().isEmpty()){
                    JOptionPane.showMessageDialog(frame,"All input fields must not be empty.");
                }else{
                    updateInventoryItem(fieldUpdateId.getText(), fieldUpdateName.getText(), fieldUpdateQuantity.getText(), fieldUpdatePrice.getText());
                    fieldUpdateId.setText("");
                    fieldUpdateName.setText("");
                    fieldUpdateQuantity.setText("");
                    fieldUpdatePrice.setText("");
                }
            }
        });
        updateItemPanel.add(btnUpdateSubmit,updateBagConstraints);
        JButton btnBackUpdate = new JButton("Back");
        updateBagConstraints.gridx = 0;
        updateBagConstraints.gridy = 5;
        updateBagConstraints.gridwidth = 2;
        btnBackUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                showPreviousPanel();
            }
        });
        updateItemPanel.add(btnBackUpdate,updateBagConstraints);

        /////////////////////////////////////////////////
        // Search Item Panel
        /////////////////////////////////////////////////

        searchItemPanel = new JPanel(new GridBagLayout());
        GridBagConstraints searchBagConstraints = new GridBagConstraints();
        searchBagConstraints.insets = new Insets(5,10,5,10);
        searchBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelSearchId = new JLabel("ID:");
        searchBagConstraints.weightx = 1;
        searchBagConstraints.gridx = 0;
        searchBagConstraints.gridy = 0;
        searchItemPanel.add(labelSearchId,searchBagConstraints);
        JTextField fieldSearchId = new JTextField();
        fieldSearchId.setPreferredSize(new Dimension(250,25));
        searchBagConstraints.gridx = 1;
        searchBagConstraints.gridy = 0;
        searchItemPanel.add(fieldSearchId,searchBagConstraints);

        JButton btnSearchSubmit = new JButton("Submit");
        btnSearchSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                searchItem(fieldSearchId.getText());
            }
        });
        searchBagConstraints.gridwidth = 2;
        searchBagConstraints.gridx = 0;
        searchBagConstraints.gridy = 1;
        searchItemPanel.add(btnSearchSubmit,searchBagConstraints);

        JButton btnBackSearch = new JButton("Back");
        btnBackSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                showPreviousPanel();
            }
        });
        searchBagConstraints.gridwidth = 2;
        searchBagConstraints.gridx = 0;
        searchBagConstraints.gridy = 2;
        searchItemPanel.add(btnBackSearch,searchBagConstraints);

        /////////////////////////////////////////////////
        // Card deck
        /////////////////////////////////////////////////
        cards.add(controlPanel, CONTROL_PANEL);
        cards.add(addItemPanel, ADD_ITEM_PANEL);
        cards.add(deleteItemPanel, DELETE_ITEM_PANEL);
        cards.add(updateItemPanel,UPDATE_ITEM_PANEL);
        cards.add(searchItemPanel, SEARCH_ITEM_PANEL);


        // divide the pane

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, inventoryPanel, cards);
        splitPane.setDividerLocation(800);

        // add split pane to the frame

        frame.add(splitPane, BorderLayout.CENTER);

        frame.setVisible(true);

    }

    private void showAddItemPanel(){
        panelHistory.push(CONTROL_PANEL);
        CardLayout cardLayout = (CardLayout) cards.getLayout();
        cardLayout.show(cards, ADD_ITEM_PANEL);
    }
    private void showDeleteItemPanel(){
        panelHistory.push(CONTROL_PANEL);
        CardLayout cardLayout = (CardLayout) cards.getLayout();
        cardLayout.show(cards, DELETE_ITEM_PANEL);
    }
    private void showUpdateItemPanel(){
        panelHistory.push(CONTROL_PANEL);
        CardLayout cardLayout = (CardLayout) cards.getLayout();
        cardLayout.show(cards, UPDATE_ITEM_PANEL);
    }
    private void showSearchItemPanel(){
        panelHistory.push(CONTROL_PANEL);
        CardLayout cardLayout = (CardLayout) cards.getLayout();
        cardLayout.show(cards, SEARCH_ITEM_PANEL);
    }

    private void showPreviousPanel(){
        if(!panelHistory.isEmpty()){
            String previousPanel = panelHistory.pop();
            CardLayout cardLayout = (CardLayout) cards.getLayout();
            cardLayout.show(cards, previousPanel);
        };
    }

    private void addItem(String id, String name, String quantity, String price){
        try{
            if(id.isEmpty() || name.isEmpty() || quantity.isEmpty() || price.isEmpty()){
                throw new IllegalArgumentException("All fields must not be empty.");
            }
            int quant = Integer.parseInt(quantity);
            double prc = Double.parseDouble(price);
            Item item = new Item(id, name, quant, prc);
            String result = inventory.addItem(item);
            updateTable();
            JOptionPane.showMessageDialog(frame, result);
        } catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(frame, e.getMessage(),"Input Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteItem(String id){
        try{
            boolean result = inventory.deleteItem(id);
            if(!result){
                throw new Exception("ID not found.");
            }
            updateTable();
            JOptionPane.showMessageDialog(frame, result);
        } catch (Exception e){
            JOptionPane.showMessageDialog(frame, e.getMessage());
        }
    }

    private void updateInventoryItem(String itemId,String itemName, String quantity, String price){
        try{
            if(itemId.isEmpty() || itemName.isEmpty() || quantity.isEmpty() || price.isEmpty()){
                throw new IllegalArgumentException("All fields must not be empty.");
            }
            Item updatedItem = new Item(itemId, itemName, Integer.parseInt(quantity), Double.parseDouble(price));
            String result = inventory.updateItem(itemId, updatedItem);
            updateTable(); 
            JOptionPane.showMessageDialog(frame, result);
        } catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(frame, e.getMessage(),"Input Error",JOptionPane.ERROR_MESSAGE);
        }
               
    }

/*     private void addItemToInventory(Item item) {
        inventory.addItem(item);
        updateTable();
    } */

    private void updateTable() {
        List<Item> items = inventory.getItems();
        tableModel.setRowCount(0);  // Clear existing data
        for (Item item : items) {
            Object[] rowData = {item.getId(), item.getName(), item.getQuantity(), item.getPrice()};
            tableModel.addRow(rowData);
        }
    }

    private void searchItem(String id){
        Item item = inventory.searchItem(id);
        if(item != null){
            tableModel.setRowCount(0);
            Object[] rowData = {item.getId(),item.getName(),item.getQuantity(),item.getPrice()};
            tableModel.addRow(rowData);
        }else{
            JOptionPane.showMessageDialog(frame,"Item not found or search field is empty.","Search Result",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void saveInventory(){
        List<Item> items = inventory.getItems();
        try{
            ItemSerializer.saveItemsToFile(items, "inventory.ser");
            JOptionPane.showMessageDialog(frame, "Inventory Saved.");
        } catch(IOException e){
            JOptionPane.showMessageDialog(frame, "Couldn't save inventory: " + e.getMessage(),"Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadInventory(){
        try{
            ArrayList<Item> items = ItemSerializer.loadItemsFromFile("inventory.ser");
            if(items != null){
                inventory = new ItemInventory();
                for(Item item : items){
                    try{
                        inventory.addItem(item);
                    } catch(IllegalArgumentException e){
                        JOptionPane.showMessageDialog(frame, "Error loading item: " + e.getMessage(), "Load Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                updateTable();
                JOptionPane.showMessageDialog(frame, "Inventory loaded.");
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to load inventory.", "Load Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException | ClassNotFoundException e){
            JOptionPane.showMessageDialog(frame, "Couldn't load inventory: " + e.getMessage(), "Load Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run(){
                try{
                    InventoryGUI window = new InventoryGUI();
                    window.frame.setVisible(true);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
