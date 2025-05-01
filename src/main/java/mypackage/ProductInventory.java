/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mypackage;

import java.util.*;
// import java.util.ArrayList;


/**
 *
 * @author cookie
 */
public class ProductInventory {

    // Maps ProductID to Product. Speeds up searching by productID.
    private Map<Integer, Product> productsMap = new HashMap<>();
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<ProductCategory> categories = new ArrayList<>();

    // Create ProductCategory and Product objects, then store all Product objects in the productsMap hashmap (keys are the ids)
    public void init() {
        // Create ProductCategory objects
        ProductCategory keyboards = new ProductCategory(
                "Keyboards",
                "Pre-built and customizable keyboards of various sizes and layouts."
        );

        ProductCategory switches = new ProductCategory(
                "Switches",
                "Various mechanical switch types for different feels and sounds."
        );

        ProductCategory keycaps = new ProductCategory(
                "Keycaps",
                "Durable and stylish keycap sets including artisan and themed options."
        );

        ProductCategory accessories = new ProductCategory(
                "Accessories & Modding Tools",
                "Tools and extras for modding, enhancing, and maintaining keyboards."
        );

        ProductCategory storage = new ProductCategory(
                "Storage & Protection",
                "Protective cases and desk mats for storing and styling your setup."
        );
        
        this.categories.add(keyboards);
        this.categories.add(switches);
        this.categories.add(keycaps);
        this.categories.add(accessories);
        this.categories.add(storage);

        // Create Product objects
        // Keyboard category
        Product keyboard60 = new Product(
                "60% Mechanical Keyboard",
                79.99,
                25,
                keyboards,
                "Black",
                "Compact layout with RGB, hot-swappable, and no regrets.", false
        );

        Product tklKeyboard = new Product(
                "Tenkeyless Mechanical Keyboard",
                89.99,
                15,
                keyboards,
                "White",
                "Full function row without the numpad – for true WASD warriors.", false
        );

        Product orthoBoard = new Product(
                "Ortholinear Keyboard",
                109.50,
                10,
                keyboards,
                "Navy Blue",
                "Grid layout keyboard that will ruin and then improve your typing forever.", false
        );

        Product screamingKeyboard = new Product(
                "Limited Edition Screaming Keyboard",
                149.99,
                5,
                keyboards,
                "Neon Green",
                "Plays a Wilhelm scream every time you hit Backspace. Unnecessary? Yes. Perfect? Also yes.", false
        );

        // Switches category
        Product cherrySwitchSet = new Product(
                "Cherry MX Switch Set - Red",
                24.50,
                100,
                switches,
                "Red",
                "Linear switches with smooth actuation and zero drama.", false
        );

        Product gateronSet = new Product(
                "Gateron Yellow Switch Set",
                22.99,
                80,
                switches,
                "Yellow",
                "Smooth like butter. Ideal for fast typists and casual butter fans.", false
        );

        Product silentU4 = new Product(
                "Gazzew Boba U4 Silent Switches",
                29.00,
                30,
                switches,
                "White",
                "Silky smooth and dead quiet – your coworkers will thank you.", false
        );

        Product chaosSwitches = new Product(
                "Mystery Switch Grab Bag",
                11.11,
                20,
                switches,
                "Rainbow",
                "100 random switches. No guarantees. You might cry. Or find enlightenment.", false
        );

        // Keycaps category
        Product pbtKeycaps = new Product(
                "PBT Doubleshot Keycaps",
                39.99,
                60,
                keycaps,
                "Black/White",
                "Durable legends and a crispy feel. Not edible, we checked.", false
        );

        Product artisanDragon = new Product(
                "Artisan Keycap - Dragon Skull",
                18.99,
                12,
                keycaps,
                "Purple",
                "Resin masterpiece that intimidates nearby switches.", false
        );

        Product gmkOcean = new Product(
                "GMK Ocean Themed Keycap Set",
                139.99,
                8,
                keycaps,
                "Teal",
                "Premium feel, deep sea vibes. Costs as much as an actual vacation.", false
        );

        Product bananaKeycap = new Product(
                "Artisan Keycap - RGB Banana",
                16.66,
                6,
                keycaps,
                "Yellow",
                "Glows. Peels. Has no business being this cute.", false
        );

        // Accessories and modding tools
        Product lubeKit = new Product(
                "Switch Lubing Kit",
                14.99,
                50,
                accessories,
                "Silver",
                "Everything you need to make your switches thicc and smooth.", false
        );

        Product stabilizerSet = new Product(
                "Screw-in Stabilizer Pack",
                12.00,
                40,
                accessories,
                "Black",
                "Reduces rattle. Increases happiness. Science.", false
        );

        Product coiledCable = new Product(
                "Custom Coiled Aviator Cable",
                29.99,
                35,
                accessories,
                "Electric Blue",
                "Braided, detachable, and 100% cooler than necessary.", false
        );

        Product modderStarterPack = new Product(
                "Ultimate Modder Starter Pack",
                69.00,
                10,
                accessories,
                "Various",
                "Includes lube, opener, tweezers, and a playlist of Lo-Fi typing sounds.", false
        );

        // Storage and protection
        Product carryCase = new Product(
                "Keyboard Carrying Case",
                29.99,
                40,
                storage,
                "Grey",
                "Protects your keyboard like it’s the crown jewels. Foam included.", false
        );

        Product deskmatCat = new Product(
                "Desk Mat - Cat Nap Edition",
                19.99,
                22,
                storage,
                "Pastel Pink",
                "Adorable, soft, and dangerously distracting.", false
        );

        Product dustCover = new Product(
                "Acrylic Dust Cover",
                12.99,
                45,
                storage,
                "Clear",
                "Keeps out dust, crumbs, and the judgment of non-keyboard people.", false
        );

        Product keyboardArmor = new Product(
                "Keyboard Armor Plating",
                199.99,
                2,
                storage,
                "Titanium",
                "Overkill? Yes. Will it survive a fall from space? Probably.", false
        );

        // Add all Product object to the productsMap hashmap
        // Keyboards
        productsMap.put(keyboard60.getId(), keyboard60);
        productsMap.put(tklKeyboard.getId(), tklKeyboard);
        productsMap.put(orthoBoard.getId(), orthoBoard);
        productsMap.put(screamingKeyboard.getId(), screamingKeyboard);

        // Switches
        productsMap.put(cherrySwitchSet.getId(), cherrySwitchSet);
        productsMap.put(gateronSet.getId(), gateronSet);
        productsMap.put(silentU4.getId(), silentU4);
        productsMap.put(chaosSwitches.getId(), chaosSwitches);

        // Keycaps
        productsMap.put(pbtKeycaps.getId(), pbtKeycaps);
        productsMap.put(artisanDragon.getId(), artisanDragon);
        productsMap.put(gmkOcean.getId(), gmkOcean);
        productsMap.put(bananaKeycap.getId(), bananaKeycap);

        // Accessories & Modding Tools
        productsMap.put(lubeKit.getId(), lubeKit);
        productsMap.put(stabilizerSet.getId(), stabilizerSet);
        productsMap.put(coiledCable.getId(), coiledCable);
        productsMap.put(modderStarterPack.getId(), modderStarterPack);

        // Storage & Protection
        productsMap.put(carryCase.getId(), carryCase);
        productsMap.put(deskmatCat.getId(), deskmatCat);
        productsMap.put(dustCover.getId(), dustCover);
        productsMap.put(keyboardArmor.getId(), keyboardArmor);

        // Put all products in array list
        // Makes subList'ing easier
        // I'm not sure if we still need the hashMap version (productsMap) but i'm keeping it for now
        this.products = new ArrayList<>(productsMap.values());
    }
    
    //========================= Product methods
    public ProductInventory addProduct(Product newbie) {
        productsMap.put(newbie.getId(), newbie);
        return this;
    }
    
    public ProductInventory removeProductById(int retireeId) {
        productsMap.remove(retireeId);
        return this;
    }
    
    public Product getProductById(int id) {
        Product prod = productsMap.get(id);
        if (prod == null) {
//            System.out.println("Product with id "+id+" not found."); // error output should probably be handled outside
            return null;
        }
        return prod;
    }
    
    // Use this when returning a product selected by customer from the menu
    // DO NOT USE getProductById BECAUSE the productId MIGHT NOT MATCH the index in the ArrayList!!!
    public Product getProductByIndex(int index) {
        return this.getAllProducts().get(index);
    }
    
    public ArrayList<Product> getProductsByCategoryName(String categoryName) {
        ArrayList<Product> results = new ArrayList<>();
        for (Product product : this.getAllProducts()) {
            if (product.getCategory().getName().equalsIgnoreCase(categoryName)) {
                results.add(product);
            }
        }
        return results;
    }
    
    public ArrayList<Product> getAllProducts() {
        return new ArrayList<>(productsMap.values());
    }
    
    public ArrayList<String> getProductNames() {
        ArrayList<String> productNames = new ArrayList<>();
        for (Product p: this.getAllProducts()) {
            productNames.add(p.getName());
        }
        return productNames;
    }
    
    public int getProductCount() {
        return productsMap.size();
    }

    public void updateProduct(Product product) {
        if (!productsMap.containsKey(product.getId())) {
            // if given Product's ID cannot be found, do nothing
            return;
        }
        productsMap.put(product.getId(), product); // auto-replace the old Product with the new one
        productsMap = new TreeMap<>(productsMap); // sort by key
    }
    
    //========================= Category methods 
    public ArrayList<ProductCategory> getAllCategories() {
        return this.categories;
    }
    
    public ArrayList<String> getAllCategoryNames() {
        ArrayList<String> categoryNames = new ArrayList<>();
        for (ProductCategory category : this.categories) {
            categoryNames.add(category.getName());
        }
        return categoryNames;
    }
    
    public ProductCategory getCategoryByIndex(int index) {
        return this.categories.get(index);
    }

    public ProductCategory getCategoryByName(String name) {
        for (ProductCategory c : this.categories) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }
    
    
    
    
    


}
