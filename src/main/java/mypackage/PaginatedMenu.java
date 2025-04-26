/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mypackage;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class PaginatedMenu extends JLineMenu {
    private int totalPages;
    private int itemsPerPage;
    private int page = 0; // current page
    
    public PaginatedMenu(String textHeader, ArrayList<String> options, String textPrompt, boolean hasBack, boolean hasExit, int itemsPerPage) {
        super(textHeader, options, textPrompt, hasBack, hasExit);
        super.numOfOptions = this.itemsPerPage = itemsPerPage; // note: superclass constructor assigns total size of options to numOfOptions 
        this.totalPages = options.size() / this.itemsPerPage; // integer division -> floors the quotient
    }
    
    @Override
    public void onLeft() {
        this.page = (this.page - 1 + this.totalPages) % this.totalPages;
        updatePage();
    }
    @Override
    public void onRight() {
        this.page = (this.page+1) % this.totalPages;
        updatePage();
    }
    
    
    public void updatePage() {
        super.firstItemIdx = getFirstItemIdx();
        super.selected = 0;
    }
    
    public int getFirstItemIdx() {
        return this.itemsPerPage * this.page;
    }
    
}
