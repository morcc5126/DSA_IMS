package dsa_ui;

import java.util.List;
import java.util.Scanner;

public class InventoryManagementSystem {
    private InventoryManager inventoryManager = new InventoryManager();;
    private Scanner scanner = new Scanner(System.in);
    
    public void start() {
        System.out.println("--- Miniral Inventory Management System ---");
        
        while (true) {
            mainMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    removeProduct();
                    break;
                case 3:
                    updateQuantity();
                    break;
                case 4:
                    updatePrice();
                    break;
                case 5:
                    searchProduct();
                    break;
                case 6:
                    viewAllProducts();
                    break;
                case 7:
                    viewLowStock();
                    break;
                case 8:
                    viewInventoryValue();
                    break;
                case 9:
                    System.out.println("Thank you for using the Inventory Management System!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
            
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }
    
    private void mainMenu() {
        System.out.println("\n----------- MAIN MENU -----------");
        System.out.println("[1] Add Product");
        System.out.println("[2] Remove Product");
        System.out.println("[3] Update Quantity");
        System.out.println("[4] Update Price");
        System.out.println("[5] Search Product");
        System.out.println("[6] View All Products");
        System.out.println("[7] View Low Stock Products");
        System.out.println("[8] View Total Inventory Value");
        System.out.println("[9] Exit");
        System.out.println("---------------------------------");
    }
    
    //Main menu functions
    private void addProduct() {
        System.out.println("\n----------- Add New Product -----------");
        
        String productId = getStringInput("Enter Product ID: ");
        String name = getStringInput("Enter Product Name: ");
        double price = getDoubleInput("Enter Price: ");
        int quantity = getIntInput("Enter Quantity: ");
        String category = getStringInput("Enter Category: ");
        String supplier = getStringInput("Enter Supplier: ");
        
        Product product = new Product(productId, name, price, quantity, category, supplier);
        inventoryManager.addProduct(product);
    }
    
    private void removeProduct() {
        System.out.println("\n----------- Remove Product -----------");
        String productId = getStringInput("Enter Product ID to remove: ");
        inventoryManager.removeProduct(productId);
    }
    
    private void updateQuantity() {
        System.out.println("\n----------- Update Quantity -----------");
        String productId = getStringInput("Enter Product ID: ");
        int newQuantity = getIntInput("Enter new quantity: ");
        inventoryManager.updateQuantity(productId, newQuantity);
    }
    
    private void updatePrice() {
        System.out.println("\n----------- Update Price -----------");
        String productId = getStringInput("Enter Product ID: ");
        double newPrice = getDoubleInput("Enter new price: ");
        inventoryManager.updatePrice(productId, newPrice);
    }
    
    private void searchProduct() {
        System.out.println("\n----------- Search Product -----------");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");
        System.out.println("3. Search by Category");
        int choice = getIntInput("Choose search option: ");
        
        switch (choice) {
            case 1:
                String productId = getStringInput("Enter Product ID: ");
                Product product = inventoryManager.searchProductById(productId);
                if (product != null) {
                    System.out.println("\nProduct Found:");
                    System.out.println(product);
                } else {
                    System.out.println("Product not found!");
                }
                break;
                
            case 2:
                String name = getStringInput("Enter product name: ");
                List<Product> nameResults = inventoryManager.searchProductsByName(name);
                displaySearchResults(nameResults, "Products matching name: " + name);
                break;
                
            case 3:
                String category = getStringInput("Enter category: ");
                List<Product> categoryResults = inventoryManager.searchProductsByCategory(category);
                displaySearchResults(categoryResults, "Products in category: " + category);
                break;
                
            default:
                System.out.println("Invalid choice!");
        }
    }
    
    private void viewAllProducts() {
        System.out.println("\n----------- All Products -----------");
        List<Product> products = inventoryManager.getAllProducts();
        displaySearchResults(products, "All Products");
    }
    
    private void viewLowStock() {
        System.out.println("\n----------- Low Stock Products -----------");
        int threshold = getIntInput("Enter low stock threshold: ");
        List<Product> lowStockProducts = inventoryManager.getLowStockProducts(threshold);
        displaySearchResults(lowStockProducts, "Low Stock Products (Threshold: " + threshold + ")");
    }
    
    private void viewInventoryValue() {
        System.out.println("\n----------- Inventory Value -----------");
        double totalValue = inventoryManager.getTotalInventoryValue();
        System.out.println("Total Inventory Value: ₱" + totalValue + "\n");
    }
    
    private void displaySearchResults(List<Product> products, String title) {
        System.out.println("\n" + title);
        System.out.println("-".repeat(title.length()));
        
        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            for (int i = 0; i < products.size(); i++) {
                System.out.println((i + 1) + ". " + products.get(i));
            }
            System.out.println("Total: " + products.size() + " product(s)");
        }
    }
    
    
    // Scanner shortcut
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().strip();
    }
    
    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().strip());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
            }
        }
    }
    
    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine().strip());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }
    
}
