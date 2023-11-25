package com.example.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**This class basically displays data in the form of a table.
 * It implements both IPrompt and IViewPage to both display data
 * and also take in the user's option if it is required
 */
public class TablePromptOption implements IPrompt,IViewPage {
    private ArrayList<ArrayList<String>> columns;
    private String question;
    private ArrayList<String> headers;
    private int choice;
    private Scanner sc;
    private int maximumRows;
    private final int maxColumnWidth = 40; //set maximum width of columns here

    /**constructor for the class
     * @param question which is the statement or question displayed
     * @param headers which is the ArrayList of column titles for the table
     * @param columns which is the ArrayList of ArrayList of strings for the
     * content of all the rows
     */
    public TablePromptOption(String question, ArrayList<String> headers, ArrayList<ArrayList<String>> columns ) {
        this.question = question;
        this.headers = headers;
        this.columns = columns;
        this.sc = new Scanner(System.in);
    }

    /**Will display the data. It calculates the width of the columns and accordingly
     * prints the borders. Furthermore, it also helps the text wrap to the
     * next line nearly to prevent overflow and excessive stretching of the table
     * for the sake of neatness and clarity
     */
    @Override
    public void perform() {
        // calculate max width of columns here based off the content inputted
        List<Integer> columnWidths = new ArrayList<>();
        for (int j = 0; j < columns.size(); ++j) {
            int maxWidth = headers.get(j).length();
            for (String cellData : columns.get(j)) {
                if (cellData.length() > maxWidth) {
                    maxWidth = cellData.length();
                }
            }
            columnWidths.add(Math.min(maxWidth, maxColumnWidth));
        }

        // create format string based off the column lengths
        StringBuilder format = new StringBuilder("| %-3s ");
        for (int width : columnWidths) {
            format.append("| %-").append(width).append("s ");
        }
        format.append("|%n");

        String lineSeparator = "+-";
        for (int width : columnWidths) {
            // change the number of dashes to match the content width and the surrounding spaces
            lineSeparator += "-".repeat(width + 2) + "+-";
        }
        lineSeparator += "+";

        System.out.println(question);
        System.out.println();
        // top border
        System.out.println(lineSeparator);
        // header titles
        Object[] formattedHeaders = new Object[headers.size() + 1];
        formattedHeaders[0] = "#";
        for (int i = 0; i < headers.size(); i++) {
            formattedHeaders[i + 1] = headers.get(i);
        }
        System.out.printf(format.toString(), formattedHeaders);

        // print line after headers
        System.out.println(lineSeparator);

        // calculate max number of rows
        int maxRows = columns.stream().mapToInt(List::size).max().orElse(0);
        this.maximumRows = maxRows;

        for (int i = 0; i < maxRows; i++) {
            boolean firstLine = true;
            boolean continueToNextRow;

            do {
                continueToNextRow = false;
                List<String> rowData = new ArrayList<>();

                for (int j = 0; j < columns.size(); ++j) {
                    String cellData = i < columns.get(j).size() ? columns.get(j).get(i) : "";
                    int cutOff = Math.min(cellData.length(), columnWidths.get(j));
                    String part = cellData.substring(0, cutOff);

                    if (cutOff < cellData.length()) {
                        continueToNextRow = true;

                        int lastSpace = part.lastIndexOf(' ');
                        if (lastSpace != -1) {
                            part = part.substring(0, lastSpace);
                            cutOff = lastSpace;
                        }
                    }

                    rowData.add(part);

                    if (cutOff < cellData.length() && i < columns.get(j).size()) {
                        columns.get(j).set(i, cellData.substring(cutOff).trim());
                    } else if (i < columns.get(j).size()) {
                        columns.get(j).set(i, "");
                    }
                }

                Object[] formattedRowData = new Object[rowData.size() + 1];
                formattedRowData[0] = firstLine ? Integer.toString(i) : "";
                for (int k = 0; k < rowData.size(); k++) {
                    formattedRowData[k + 1] = rowData.get(k);
                }

                System.out.printf(format.toString(), formattedRowData);
                firstLine = false; // no longer the first line

            } while (continueToNextRow);

            // print line separator after each entry except after the last entry
            if (i < maxRows - 1) {
                System.out.println(lineSeparator);
            }
        }

        // print the bottom border
        System.out.println(lineSeparator);
    }

    /**will begin the prompting process. this method can be called only
     * if user input is required. if the table just needs to be displayed
     * you don't need this method
     */
    @Override
    public void startPrompt(){
        //this is in case we want to
        perform();
        System.out.println("Enter the number of your choice:");
        while (!sc.hasNextInt()) {
            System.out.println("That's not a number. Please enter a valid number.");
            sc.next();
        }
        this.choice = sc.nextInt();
        sc.nextLine();

        if (this.choice < 0 || this.choice >= maximumRows) {
            System.out.println("Invalid choice.");
            this.choice = -1; // Indicate an invalid choice
        }
    }

    /**getter method to retrieve result
     * @return the integer choice converted to a string
     */
    public String getResult() {
        return Integer.toString(this.choice);
    }
}





