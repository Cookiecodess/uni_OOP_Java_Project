///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package mypackage;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
//
///**
// *
// * @author cookie
// */
//public class TestProduct {
//    static Scanner scanner = new Scanner(System.in);
//    static JLineMenu browsePage;
//
//    public static void main(String args[]) {
//        initAllMenus();
//        
//        // Initialize inventory
//        //ProductInventory inv = new ProductInventory();        
//        //inv.init();
//        inv = Main.inventory;
//
//        enterBrowsePage(inv);
//        
//    }
//    
//    public static void enterBrowsePage(ProductInventory inv) {
//        while (true) {
//            int selection = browsePage.drawMenu();
//            if (selection == JLineMenu.BACK_OPTION) {
//                return;
//            }
//            switch (selection) {
//                case 0 -> { 
//                    browseByCat(inv);
//                    continue;
//                }
//                case 1 -> {
//                    continue;
//                }
//                default -> {
//                    continue;
//                }
//            }            
//        }
//    }
//    
//    public static void browseByCat(ProductInventory inv) {
//        JLineMenu categoryMenu = new JLineMenu("Browse by Category", inv.getAllCategories(), "What kind of products would you like to buy?", true, true);
//        while (true) {
//            
//        }
//    }
//    
//    /*
//    public static void enterBrowsePage(ProductInventory inv) {
//        browsePage = new JLineMenu("Browse", inv.getProductNames(), "Select an action to continue.", false, true);
//        while (true) {
//            int selection = browsePage.drawMenu();
//            if (selection == JLineMenu.BACK_OPTION) {
//                return;
//            }
//            switch (selection) {
//                case 0 -> { 
//                    continue;
//                }
//                case 1 -> {
//                    continue;
//                }
//                case 2 -> {
//                    continue;
//                }
//                default -> {
//                    continue;
//                }
//            }            
//        }
//    }
//*/
//    
//    public static void initAllMenus() {
//        // initialize menus
//        ArrayList<String> options = new ArrayList<>();
//        options.add("Browse by Category");        
//        options.add("Browse by Name");      
//        browsePage = new JLineMenu("Browse", options, "Buy buy buy ;)", false, true);
//    }
//    
//}
