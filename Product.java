package dsa_ui;

public class Product {
    private String productId;
    private String name;
    private double price;
    private int quantity;
    private String category;
    private String supplier;
    
    public Product(String productId, String name, double price, int quantity, String category, String supplier) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.supplier = supplier;
    }

    //Getters and Setters
    public String getProductId() { return productId; }
    public String getName()      { return name; }
    public double getPrice()     { return price; }
    public int getQuantity()     { return quantity; }
    public String getCategory()  { return category; }
    public String getSupplier()  { return supplier; }
                   
    public void setProductId(String productId) { this.productId = productId; }
    public void setName(String name)           { this.name = name; }
    public void setPrice(double price)         { this.price = price; }
    public void setQuantity(int quantity)      { this.quantity = quantity; }
    public void setCategory(String category)   { this.category = category; }
    public void setSupplier(String supplier)   { this.supplier = supplier; }
    
    public String toString() { //di ko alam ginagawa nito nakita ko lang ito sa github
        return String.format("ID: %s | Name: %s | Price: $%.2f | Quantity: %d | Category: %s | Supplier: %s", productId, name, price, quantity, category, supplier);
    }
}
