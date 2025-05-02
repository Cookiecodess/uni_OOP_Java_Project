/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mypackage;

/**
 *
 * @author cookie
 */
public class ProductCategory implements MenuItem {
    private int id;
    private String name;
    private String description;

    private static int nextId = 0; // initialize id with 0    

    public ProductCategory(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public ProductCategory(String name, String description) {
        this(nextId++, name, description);
    }
    
    public ProductCategory(String name) {
        this(nextId++, name, "");
    }

    public ProductCategory() {
        this(nextId++, "", "");
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

    public String getDescription() {
        if (description.isBlank()) {
            return "No description.";
        }
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // methods for MenuItem interface
    public String getItemLabel() {
        return this.name;
    }
    
    public void printInfo() {
        System.out.println("Description: "+this.description);
    }

    @Override
    public ProductCategory clone() {
        ProductCategory clone = new ProductCategory(this.id, this.name, this.description);
        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductCategory that = (ProductCategory) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }
    
}
