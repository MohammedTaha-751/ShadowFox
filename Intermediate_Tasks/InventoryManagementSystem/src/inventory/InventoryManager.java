package inventory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryManager {

    private JFrame frame;
    private DefaultListModel<String> itemListModel;
    private JList<String> itemList;
    private JTextField nameField;
    private JTextField quantityField;

    private List<Item> inventory;
    private int nextId = 1;

    public InventoryManager() {
        inventory = new ArrayList<>();

        // Frame setup
        frame = new JFrame("Inventory Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 350);
        frame.setLayout(new BorderLayout());

        // Top Panel: Input fields and buttons
        JPanel topPanel = new JPanel(new GridLayout(2, 1));

        JPanel inputPanel = new JPanel();
        nameField = new JTextField(12);
        quantityField = new JTextField(5);
        inputPanel.add(new JLabel("Item Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Qty:"));
        inputPanel.add(quantityField);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update Selected");
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);

        topPanel.add(inputPanel);
        topPanel.add(buttonPanel);

        // Center: Item list
        itemListModel = new DefaultListModel<>();
        itemList = new JList<>(itemListModel);
        JScrollPane scrollPane = new JScrollPane(itemList);

        // Bottom: Delete button
        JPanel bottomPanel = new JPanel();
        JButton deleteButton = new JButton("Delete Selected");
        bottomPanel.add(deleteButton);

        // Add components to frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Add item
        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String qtyText = quantityField.getText().trim();

            if (!name.isEmpty() && qtyText.matches("\\d+")) {
                int qty = Integer.parseInt(qtyText);
                Item newItem = new Item(nextId++, name, qty);
                inventory.add(newItem);
                itemListModel.addElement(newItem.toString());
                nameField.setText("");
                quantityField.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "Enter valid name and quantity (numeric).");
            }
        });

        // Update item
        updateButton.addActionListener(e -> {
            int selectedIndex = itemList.getSelectedIndex();
            String name = nameField.getText().trim();
            String qtyText = quantityField.getText().trim();

            if (selectedIndex != -1 && !name.isEmpty() && qtyText.matches("\\d+")) {
                int qty = Integer.parseInt(qtyText);
                Item item = inventory.get(selectedIndex);
                item.setName(name);
                item.setQuantity(qty);
                itemListModel.set(selectedIndex, item.toString());
                nameField.setText("");
                quantityField.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "Select an item and enter valid data to update.");
            }
        });

        // Delete item
        deleteButton.addActionListener(e -> {
            int selectedIndex = itemList.getSelectedIndex();
            if (selectedIndex != -1) {
                inventory.remove(selectedIndex);
                itemListModel.remove(selectedIndex);
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InventoryManager::new);
    }

    // Inner class for inventory items
    static class Item {
        private int id;
        private String name;
        private int quantity;

        public Item(int id, String name, int quantity) {
            this.id = id;
            this.name = name;
            this.quantity = quantity;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return "[ID: " + id + "] " + name + " - Qty: " + quantity;
        }
    }
}
