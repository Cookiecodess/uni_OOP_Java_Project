/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mypackage;

import java.util.ArrayList;

/**
 *
 * @author cookie
 */
public class Product implements MenuItem {    
    private int id;    
    private String name;
    private double price;
    private int stock;
    private ProductCategory category;
    private String color;
    private String description;
//    private ArrayList<ProductReview> reviews; // wait for teammate to write Review class
    private boolean isDiscontinued;
    
    private static int nextId = 0; // initialize id with 0    
    
    // Used by loadProductsFromCSV
    public Product(int id, String name, double price, int stock, ProductCategory category, String color,
            String description, boolean isDiscontinued) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.color = color;
        this.description = description;
        this.isDiscontinued = isDiscontinued;
    }

    // Used throughout the program
    public Product(String name, double price, int stock, ProductCategory category, String color, String description, boolean isDiscontinued) {
        this(nextId++, name, price, stock, category, color, description, isDiscontinued); // id auto-increments
    }

    public Product() {
        this("", 0.0, 0, null, "", "", false);
    }
    
    public void addStock(int amount) {
        this.stock += amount;
    }
    
    public boolean minusStock(int amount) {
        if (amount > this.stock) {
            return false;
        }
        this.stock -= amount;
        return true;
    }
    
    /*
    public ArrayList<ProductReview> getAllReviews() {
        return this.reviews;
    }
    */
    
    /*
    public void addReview(Customer cust, double rating, String reviewText) {
        this.reviews.add(new ProductReview(cust, rating, reviewText));
    }
    */
    
    /*
    public double getAvgRating() {
        double sumOfRatings = 0;
        double totalReviews = reviews.size();
        for (ProductReview r: reviews) {
            sumOfRatings += r.getRating();
        }
        return sumOfRatings / totalReviews;
    }
    */
    
    /**
     * 
     * @return true if this Product is discontinued, false if not
     */
    public boolean isDiscontinued() {
        return this.isDiscontinued;
    }

    /**
     * Discontinue a Product if it's on sale, or reinstate it if it's discontinued.
     */
    public void toggleDiscontinuation() {
        this.isDiscontinued = !this.isDiscontinued;
    }


    
    // Getters and setters
    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int newNextId) {
        nextId = newNextId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDiscontinuation(boolean isDiscontinued) {
        this.isDiscontinued = isDiscontinued;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", price=" + price + ", stock=" + stock + ", category=" + category + ", color=" + color + ", description=" + description + '}';
    }

    // Object.clone() is protected, so to use clone() publicly, we must override it
    @Override
    public Product clone() {
        Product clone = new Product(this.name, this.price, this.stock, this.category, this.color, this.description, this.isDiscontinued);
        clone.id = this.id; // have to do this to override the auto-incrementing ID behavior
        return clone;
    }

    // METHODS FOR MenuItem INTERFACE ========================================
    public String getItemLabel() {
        return this.name;
    }

    public void printInfo() {
        Table productInfoTable = new Table(2, 4);
        productInfoTable.add("ID", String.valueOf(this.id));
        productInfoTable.add("Description", this.description);
        productInfoTable.add("Unit Price", "RM "+String.format("%.2f", this.price));
        productInfoTable.add("Stock available", String.valueOf(this.stock));
        productInfoTable.add("Category", this.category.getName());
        productInfoTable.add("Color", this.color);
        String status = this.isDiscontinued ? "Discontinued" : "On sale";
        productInfoTable.add("Status", status);

        productInfoTable.print();
    }

    public boolean isDisabled() {
        return this.stock == 0 || this.isDiscontinued;
    }

    
    
}
