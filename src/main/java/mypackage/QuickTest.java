package mypackage;

import java.util.List;
import java.util.Comparator;

public class QuickTest {
    public static void main(String[] args) {
        ProductInventory inv = new ProductInventory();
        inv.init();

        List<Product> products = inv.getAllProducts();
        // List<Product> products = inv.getProductsByCategoryName("Keyboards");
        // products.addAll(inv.getProductsByCategoryName("Switches"));
        products.sort(Comparator.comparing(Product::getName));
        // Comparator<Product> c = (p1, p2) -> p1.getName().compareTo(p2.getName());
        // products.sort(c);
        for (Product product: products) {
            System.out.println(product.getName());
        }
    }
}