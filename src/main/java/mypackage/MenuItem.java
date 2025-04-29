package mypackage;

public interface MenuItem {
    String getItemLabel(); // for printing the option list in a menu
    void printInfo(); // for printing additional info at the bottom of a menu
    
    default boolean isDisabled() {
        return false;
    }; // for checking if a menu item is disabled (i.e not selectable)
}