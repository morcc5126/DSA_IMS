package dsa_ui;

import java.util.*;

public class InventoryManager {
    private Map<String, Product> products = new HashMap<>();
    
    
    // Add a new product
    public boolean addProduct(Product product) {
        if (products.containsKey(product.getProductId())) {
            System.out.println("Product with ID " + product.getProductId() + " already exists!");
            return false; //pangpa-end lang ng method yung return, wala itong ginagawa
        }
        products.put(product.getProductId(), product);
        System.out.println("Product added successfully!");
        return true;
    }
    
    // Remove a product
    public boolean removeProduct(String productId) {
        if (!products.containsKey(productId)) {
            System.out.println("Product with ID " + productId + " not found!");
            return false; //pangpa-end lang ng method yung return, wala rin itong ginagawa
        }
        Product removed = products.remove(productId);
        System.out.println("Product " + removed.getName() + " removed successfully!");
        return true;
    }
    
    // Update product quantity
    public boolean updateQuantity(String productId, int newQuantity) {
        Product product = products.get(productId);
        if (product == null) {
            System.out.println("Product with ID " + productId + " not found!");
            return false; //pangpa-end lang ng method yung return, wala rin itong ginagawa
        }
        product.setQuantity(newQuantity);
        System.out.println("Quantity updated successfully!");
        return true;
    }
    
    // Update product price
    public boolean updatePrice(String productId, double newPrice) {
        Product product = products.get(productId);
        if (product == null) {
            System.out.println("Product with ID " + productId + " not found!");
            return false; //pangpa-end lang ng method yung return, wala rin itong ginagawa
        } else {
        product.setPrice(newPrice);
        System.out.println("Price updated successfully!");
        return true;
        }
                
    }
    //Search function    
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
}
