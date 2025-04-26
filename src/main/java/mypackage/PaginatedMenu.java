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
        this.itemsPerPage = itemsPerPage;
        this.totalPages = options.size() / this.itemsPerPage; // integer division -> floors the quotient
    }
    
    @Override
    public void onLeft() {
        this.page = (this.page-1) % this.totalPages;
        super.selected = 0;
    }
    @Override
    public void onRight() {
        this.page = (this.page+1) % this.totalPages;
        super.selected = 0;
    }
    
    @Override
    public void drawOptions() {
        for (int i = 0; i < super.numOfOptions; i++) {
            if (i == selected) {
                // Highlight selected item
//                terminal.writer().println("\u001b[7m> " + this.options.get(i) + "\u001b[0m");
            } else {
//                terminal.writer().println("  " + this.options.get(i));
            }
        }
    }
    
    public ArrayList<String> getPageItems() {
        int firstItemIdx = this.itemsPerPage * this.page;
        return new ArrayList<>(super.options.subList(firstItemIdx, (firstItemIdx + this.itemsPerPage)));
    }
}
