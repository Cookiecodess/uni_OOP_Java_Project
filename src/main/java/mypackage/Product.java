/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mypackage;

/**
 *
 * @author cookie
 */
public class Product {    
    private int id;    
    private String name;
    private double price;
    private int stock;
    private ProductCategory category;
    private String color;
    private String description;
    
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
    
    public void minusStock(int amount) {
        this.stock -= amount;
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

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", price=" + price + ", stock=" + stock + ", category=" + category + ", color=" + color + ", description=" + description + '}';
    }
    
}
