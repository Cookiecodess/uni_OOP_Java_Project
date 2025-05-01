package mypackage;

import java.io.IOException;
import java.util.*;

import org.jline.terminal.Terminal;

public class DropdownMenu {
    private List<MenuItem> menuItems;
    private String prompt;
    private int currentSelection;
    private boolean isItemSelected = false;    

    static Terminal terminal = JLineMenu.terminal;

    public DropdownMenu(List<MenuItem> menuItems, String prompt) {
        this.menuItems = menuItems;
        this.prompt = prompt;
        this.currentSelection = 0;
    }

    public int draw() {
        while (true) {
            MenuItem currentItem = this.menuItems.get(currentSelection);

            System.out.print(JLineMenu.SAV_CUR + Helper.CLR_LINE);
            System.out.print(prompt);
            System.out.println(JLineMenu.BOLD + currentItem.getItemLabel() + JLineMenu.RESET);

            // Draw menu items with currently selected one highlighted
            for (int i=0; i<this.menuItems.size(); i++) {
                if (i == currentSelection) {
                    // Highlight currentSelection item
                    terminal.writer().println("\u001b[7m> " + menuItems.get(i).getItemLabel() + JLineMenu.RESET);
                } else {
                    terminal.writer().println("  " + menuItems.get(i).getItemLabel());
                }
            }

            // Print additional info
            System.out.println("=".repeat(40));
            currentItem.printInfo();

            try {
                handleKeyPress();

                if (isItemSelected) {
                    clearMenuItems();
                    return currentSelection;
                }

                clearPromptLine();

            } catch (IOException e) {
                System.err.println("Oops seems like we can't use the Terminal reader? Error: " + e.getMessage());
            }
        }
    }

    public void handleKeyPress() throws IOException {
        // Handle key presses
        int c = terminal.reader().read();
        switch (c) {
            case 27 -> { // 1st byte of an arrow keypress: ESC
                terminal.reader().read(); // read 2nd byte of arrow keypress: 91 ([) or 79
                c = terminal.reader().read();
                switch (c) {
                    case 65 ->
                        moveCursorUp();
                    case 66 ->
                        moveCursorDown();
                    // case 67 ->
                    //     System.out.println("right");
                    // case 68 ->
                    //     System.out.println("left");
                }
            }
            case 13 -> {
                // Enter pressed
                selectItem();
            }
        }
    }

    public void moveCursorUp() {
        currentSelection = (currentSelection - 1 + this.menuItems.size()) % this.menuItems.size();
        // do {
        //     currentSelection = (currentSelection - 1 + this.numOfOptions) % this.numOfOptions;
        // } while (this.options.get(currentSelection).isDisabled());
    }

    public void moveCursorDown() {
        currentSelection = (currentSelection + 1) % this.menuItems.size();
        // do {
        //     currentSelection = (currentSelection + 1) % this.numOfOptions;
        // } while (this.options.get(currentSelection).isDisabled());
    }

    public void selectItem() {
        isItemSelected = true;
    }

    public void clearPromptLine() {
        // Move cursor to the prompt line
        // System.out.print(Helper.CUR_UP.repeat(menuItems.size() + 3));
        System.out.print(JLineMenu.RST_CUR);
        // Clear line
        System.out.print(Helper.CLR_LINE);
    }

    public void clearMenuItems() {
        System.out.print(JLineMenu.RST_CUR);
        Helper.clearLinesBelow(20);
        // System.out.print(Helper.CUR_DOWN);
    }

}