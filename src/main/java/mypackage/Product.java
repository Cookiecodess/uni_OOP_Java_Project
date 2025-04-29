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
public class Product implements MenuItem{    
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
    
    public Product(String name, double price, int stock, ProductCategory category, String color, String description) {
        this.id = nextId++; // id auto-increments
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.color = color;
        this.description = description;
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
     * Discontinue a Product.
     */
    public void discontinue() {
        this.isDiscontinued = true;
    }
    
    // Getters and setters
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

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", price=" + price + ", stock=" + stock + ", category=" + category + ", color=" + color + ", description=" + description + '}';
    }

    // MenuOption methods
    @Override
    public String getItemLabel() {
        return this.name;
    }

    @Override
    public void printInfo() {
        System.out.println("Description: \t\t"+this.description);
        System.out.println("Unit Price: \t\tRM "+this.price);
        System.out.println("Stock available: \t"+this.stock);
        System.out.println("Category: \t\t"+this.category.getName());
        System.out.println("Color: \t\t\t"+this.color);
    }
    
}
