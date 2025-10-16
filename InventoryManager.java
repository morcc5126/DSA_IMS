/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsa_ui;

import java.util.*;
import java.io.*;

public class InventoryManager {
    private Map<String, Product> products;
    private static final String FILE_NAME = "inventory.dat";
    
    public InventoryManager() {
        products = new HashMap<>();
        loadInventoryFromFile();
    }
    
    // Add a new product
    public boolean addProduct(Product product) {
        if (products.containsKey(product.getProductId())) {
            System.out.println("Product with ID " + product.getProductId() + " already exists!");
            return false;
        }
        products.put(product.getProductId(), product);
        saveInventoryToFile();
        System.out.println("Product added successfully!");
        return true;
    }
    
    // Remove a product
    public boolean removeProduct(String productId) {
        if (!products.containsKey(productId)) {
            System.out.println("Product with ID " + productId + " not found!");
            return false;
        }
        Product removed = products.remove(productId);
        saveInventoryToFile();
        System.out.println("Product " + removed.getName() + " removed successfully!");
        return true;
    }
    
    // Update product quantity
    public boolean updateQuantity(String productId, int newQuantity) {
        Product product = products.get(productId);
        if (product == null) {
            System.out.println("Product with ID " + productId + " not found!");
            return false;
        }
        product.setQuantity(newQuantity);
        saveInventoryToFile();
        System.out.println("Quantity updated successfully!");
        return true;
    }
    
    // Update product price
    public boolean updatePrice(String productId, double newPrice) {
        Product product = products.get(productId);
        if (product == null) {
            System.out.println("Product with ID " + productId + " not found!");
            return false;
        }
        product.setPrice(newPrice);
        saveInventoryToFile();
        System.out.println("Price updated successfully!");
        return true;
    }
    
    // Search product by ID
    public Product searchProductById(String productId) {
        return products.get(productId);
    }
    
    // Search products by name
    public List<Product> searchProductsByName(String name) {
        List<Product> result = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }
    
    // Search products by category
    public List<Product> searchProductsByCategory(String category) {
        List<Product> result = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                result.add(product);
            }
        }
        return result;
    }
    
    // Get low stock products
    public List<Product> getLowStockProducts(int threshold) {
        List<Product> result = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.getQuantity() <= threshold) {
                result.add(product);
            }
        }
        return result;
    }
    
    // Get all products
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }
    
    // Get total inventory value
    public double getTotalInventoryValue() {
        double total = 0;
        for (Product product : products.values()) {
            total += product.getPrice() * product.getQuantity();
        }
        return total;
    }
    
    // Save inventory to file
    private void saveInventoryToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(products);
        } catch (IOException e) {
            System.out.println("Error saving inventory to file: " + e.getMessage());
        }
    }
    
    // Load inventory from file
    @SuppressWarnings("unchecked")
    private void loadInventoryFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            products = (Map<String, Product>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No existing inventory file found. Starting with empty inventory.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading inventory from file: " + e.getMessage());
        }
    }
}