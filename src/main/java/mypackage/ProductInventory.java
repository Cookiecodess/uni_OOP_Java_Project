/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mypackage;

import java.util.HashMap;
import java.util.ArrayList;


/**
 *
 * @author cookie
 */
public class ProductInventory {

    private HashMap<Integer, Product> productsMap = new HashMap<>();

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

        // Create Product objects
        // Keyboard category
        Product keyboard60 = new Product(
                "60% Mechanical Keyboard",
                79.99,
                25,
                keyboards,
                "Black",
                "Compact layout with RGB, hot-swappable, and no regrets."
        );

        Product tklKeyboard = new Product(
                "Tenkeyless Mechanical Keyboard",
                89.99,
                15,
                keyboards,
                "White",
                "Full function row without the numpad – for true WASD warriors."
        );

        Product orthoBoard = new Product(
                "Ortholinear Keyboard",
                109.50,
                10,
                keyboards,
                "Navy Blue",
                "Grid layout keyboard that will ruin and then improve your typing forever."
        );

        Product screamingKeyboard = new Product(
                "Limited Edition Screaming Keyboard",
                149.99,
                5,
                keyboards,
                "Neon Green",
                "Plays a Wilhelm scream every time you hit Backspace. Unnecessary? Yes. Perfect? Also yes."
        );

        // Switches category
        Product cherrySwitchSet = new Product(
                "Cherry MX Switch Set - Red",
                24.50,
                100,
                switches,
                "Red",
                "Linear switches with smooth actuation and zero drama."
        );

        Product gateronSet = new Product(
                "Gateron Yellow Switch Set",
                22.99,
                80,
                switches,
                "Yellow",
                "Smooth like butter. Ideal for fast typists and casual butter fans."
        );

        Product silentU4 = new Product(
                "Gazzew Boba U4 Silent Switches",
                29.00,
                30,
                switches,
                "White",
                "Silky smooth and dead quiet – your coworkers will thank you."
        );

        Product chaosSwitches = new Product(
                "Mystery Switch Grab Bag",
                11.11,
                20,
                switches,
                "Rainbow",
                "100 random switches. No guarantees. You might cry. Or find enlightenment."
        );

        // Keycaps category
        Product pbtKeycaps = new Product(
                "PBT Doubleshot Keycaps",
                39.99,
                60,
                keycaps,
                "Black/White",
                "Durable legends and a crispy feel. Not edible, we checked."
        );

        Product artisanDragon = new Product(
                "Artisan Keycap - Dragon Skull",
                18.99,
                12,
                keycaps,
                "Purple",
                "Resin masterpiece that intimidates nearby switches."
        );

        Product gmkOcean = new Product(
                "GMK Ocean Themed Keycap Set",
                139.99,
                8,
                keycaps,
                "Teal",
                "Premium feel, deep sea vibes. Costs as much as an actual vacation."
        );

        Product bananaKeycap = new Product(
                "Artisan Keycap - RGB Banana",
                16.66,
                6,
                keycaps,
                "Yellow",
                "Glows. Peels. Has no business being this cute."
        );

        // Accessories and modding tools
        Product lubeKit = new Product(
                "Switch Lubing Kit",
                14.99,
                50,
                accessories,
                "Silver",
                "Everything you need to make your switches thicc and smooth."
        );

        Product stabilizerSet = new Product(
                "Screw-in Stabilizer Pack",
                12.00,
                40,
                accessories,
                "Black",
                "Reduces rattle. Increases happiness. Science."
        );

        Product coiledCable = new Product(
                "Custom Coiled Aviator Cable",
                29.99,
                35,
                accessories,
                "Electric Blue",
                "Braided, detachable, and 100% cooler than necessary."
        );

        Product modderStarterPack = new Product(
                "Ultimate Modder Starter Pack",
                69.00,
                10,
                accessories,
                "Various",
                "Includes lube, opener, tweezers, and a playlist of Lo-Fi typing sounds."
        );

        // Storage and protection
        Product carryCase = new Product(
                "Keyboard Carrying Case",
                29.99,
                40,
                storage,
                "Grey",
                "Protects your keyboard like it’s the crown jewels. Foam included."
        );

        Product deskmatCat = new Product(
                "Desk Mat - Cat Nap Edition",
                19.99,
                22,
                storage,
                "Pastel Pink",
                "Adorable, soft, and dangerously distracting."
        );

        Product dustCover = new Product(
                "Acrylic Dust Cover",
                12.99,
                45,
                storage,
                "Clear",
                "Keeps out dust, crumbs, and the judgment of non-keyboard people."
        );

        Product keyboardArmor = new Product(
                "Keyboard Armor Plating",
                199.99,
                2,
                storage,
                "Titanium",
                "Overkill? Yes. Will it survive a fall from space? Probably."
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

    }
    
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
    
    public ArrayList<Product> getProductArray() {
        return new ArrayList<Product>(productsMap.values());
    }
    
    public int getProductCount() {
        return productsMap.size();
    }


}
