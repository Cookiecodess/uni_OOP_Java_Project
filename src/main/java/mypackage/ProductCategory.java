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
    private String name;
    private String description;

    public ProductCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    public ProductCategory(String name) {
        this(name, "");
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
    
}
