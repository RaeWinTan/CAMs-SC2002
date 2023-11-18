package com.example.view;

import java.util.ArrayList;

import com.example.utility.Pair;

public abstract class DisplayTableDataPage {

    private ArrayList<Pair<String, String>> data;

    public DisplayTableDataPage(ArrayList<Pair<String, String>> data) {
        this.data = data;
    }

    public abstract void Display(ArrayList<Pair<String, String>> data);

    public abstract ArrayList<String> getHeaders();

    public void displayData() {
        ArrayList<String> headers = getHeaders();
        int[] columnWidths = calculateColumnWidths(headers);

        printBorder(columnWidths);
        printRow(headers, columnWidths);
        printBorder(columnWidths);
        for (Pair<String, String> entry : data) {
            ArrayList<String> row = new ArrayList<>();
            row.add(entry.getFirst());
            row.add(entry.getSecond());
            printRow(row, columnWidths);
        }
        printBorder(columnWidths);
    }

    private int[] calculateColumnWidths(ArrayList<String> headers) {
        int[] columnWidths = new int[headers.size()];
        for (int i = 0; i < headers.size(); i++) {
            columnWidths[i] = headers.get(i).length();
        }
        for (Pair<String, String> entry : data) {
            columnWidths[0] = Math.max(columnWidths[0], entry.getFirst().length());
            columnWidths[1] = Math.max(columnWidths[1], entry.getSecond().length());
        }
        return columnWidths;
    }

    private void printBorder(int[] columnWidths) {
        for (int width : columnWidths) {
            System.out.print("+");
            System.out.print(new String(new char[width + 2]).replace("\0", "-")); // "+2" for padding
        }
        System.out.println("+");
    }

    private void printRow(ArrayList<String> row, int[] columnWidths) {
        System.out.print("|");
        for (int i = 0; i < row.size(); i++) {
            String format = " %-"+ columnWidths[i] +"s |";
            System.out.printf(format, row.get(i));
        }
        System.out.println();
    }
}
