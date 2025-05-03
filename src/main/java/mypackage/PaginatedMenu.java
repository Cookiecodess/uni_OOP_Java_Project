// /*
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
//  */
// package mypackage;

// import java.util.ArrayList;

// /**
//  *
//  * @author User
//  */
// public class PaginatedMenu extends JLineMenu {
//     private int totalNumOfItems;
//     private int totalPages;
//     private int itemsPerPage;
//     private int page = 0; // current page
    
//     public PaginatedMenu(String textHeader, ArrayList<String> options, String textPrompt, boolean hasBack, boolean hasExit, int itemsPerPage) {
//         super(textHeader, options, textPrompt, hasBack, hasExit);
//         this.itemsPerPage = itemsPerPage; 
//         super.setNumOfOptions(this.itemsPerPage); // note: superclass constructor assigns total size of options to numOfOptions 
//         this.totalNumOfItems = options.size();
//         this.totalPages = this.totalNumOfItems / this.itemsPerPage; // integer division -> floors the quotient
//     }
    
//     @Override
//     public void onLeft() {
//         prevPage();
//         updatePage();
//     }
//     @Override
//     public void onRight() {
//         nextPage();
//         updatePage();
//     }
//     @Override
//     public void moveCursorUp() {
//         if (super.getCurrentSelection() < this.getFirstItemIdx()) {
//             if (this.page == 0) {
//                 this.page = this.totalPages - 1; // jump to last page
//                 super.setCurrentSelection(this.totalNumOfItems - 1); // jump to last item
//             } else {
                
//             }
//         }
//     }
    
//     public void prevPage() {
//         this.page = (this.page - 1 + this.totalPages) % this.totalPages;
//     }
//     public void nextPage() {
//         this.page = (this.page+1) % this.totalPages;
//     }
    
    
//     public void updatePage() {
//         super.firstItemIdx = getFirstItemIdx();
//         super.setCurrentSelection(0);
//         if (this.page == this.totalPages - 1) {
//             // if it's the last page
//             int lastPageItemCount = this.totalNumOfItems % this.itemsPerPage;
//             super.setNumOfOptions(lastPageItemCount);
//         }
//     }
    
//     public int getFirstItemIdx() {
//         return this.itemsPerPage * this.page;
//     }
    
// }
