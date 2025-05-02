package mypackage;

import java.util.*;

/** 
 * See runExample() for an example of usage.
 */
public class Table {
    private List<List<String>> rows;
    private int numOfColumns;
    private int gap;
    private List<Integer> columnLengths;
    private boolean hasIndex;

    public Table(List<List<String>> rows, int numOfColumns, int gap) {
        this.rows = rows;
        this.numOfColumns = numOfColumns;
        this.gap = gap;
        this.hasIndex = false;

        // Initialize columnLengths with `columns` number of zeroes.
        this.columnLengths = new ArrayList<Integer>(Collections.nCopies(numOfColumns, 0));
    }    

    public Table(int columns, int gap) {
        this(new ArrayList<>(), columns, gap);
    }

    public void add(String... cells) {
        List<String> newRow = Arrays.asList(cells);
        this.rows.add(newRow);
    }

    public void remove(int index) {
        this.rows.remove(index);
    }

    public void setRow(int index, String... cells) {
        List<String> updated = Arrays.asList(cells);
        this.rows.set(index, updated);
    }

    public void setCell(int row, int col, String updated) {
        this.rows.get(row).set(col, updated);
    }

    public void setIndex(boolean trueOrFalse) {
        this.hasIndex = trueOrFalse;
    }

    private void calcColumnLengths() {
        for (List<String> row : rows) {
            for (int col = 0; col < this.numOfColumns; col++) {
                int cellLength = row.get(col).length();
                if (cellLength > columnLengths.get(col)) {
                    columnLengths.set(col, cellLength);
                }
            }
        }

        for (int i=0; i<columnLengths.size(); i++) {
            columnLengths.set(i, columnLengths.get(i)+this.gap);
        }
    }

    private void printSpaces(int n) {
        for (int times=0; times<n; times++) {
            System.out.print(" ");
        }
    }

    public void print() {   
        this.calcColumnLengths();
             
        for (int r=0; r<rows.size(); r++) {
            List<String> row = rows.get(r);
            if (hasIndex) {
                if (r<10) {
                    System.out.print(" ");
                } 
                System.out.print(r+1);
                this.printSpaces(this.gap);
            }
            for (int col=0; col < this.numOfColumns; col++) {
                int spaces = this.columnLengths.get(col) - row.get(col).length();
                System.out.print(row.get(col));
                this.printSpaces(spaces);
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        runExample();
    }

    public static void runExample() {

        Product product = Main.inventory.getProductById(5);

        Table productDetailsTable = new Table(2, 5);
        productDetailsTable.add("ID", String.valueOf(product.getId()));
        productDetailsTable.add("Name", product.getName());
        productDetailsTable.add("Price", "RM " + String.valueOf(product.getId()));
        productDetailsTable.add("Stock", String.valueOf(product.getStock()));
        productDetailsTable.add("Category", product.getCategory().getName());
        productDetailsTable.add("Color", product.getColor());
        productDetailsTable.add("Description", product.getDescription());
        String status = product.isDiscontinued() ? "Discontinued" : "On sale";
        productDetailsTable.add("Status", status);

        productDetailsTable.setIndex(true);
        productDetailsTable.print();
    }

    
}