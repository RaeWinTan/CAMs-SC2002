package com.example.view;

import java.util.ArrayList;

import com.example.utility.Pair;

public abstract class DisplayTableDataPage implements IViewPage{

    private ArrayList<Pair<String, String>> data;

    public DisplayTableDataPage(ArrayList<Pair<String, String>> data) {
        this.data = data;
    }

    

    protected abstract ArrayList<String> getHeaders();

    public void perform() {
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

    protected int[] calculateColumnWidths(ArrayList<String> headers) {
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

    protected void printBorder(int[] columnWidths) {
        for (int width : columnWidths) {
            System.out.print("+");
            System.out.print(new String(new char[width + 2]).replace("\0", "-")); // "+2" for padding
        }
        System.out.println("+");
    }

    protected void printRow(ArrayList<String> row, int[] columnWidths) {
        System.out.print("|");
        for (int i = 0; i < row.size(); i++) {
            String format = " %-"+ columnWidths[i] +"s |";
            System.out.printf(format, row.get(i));
        }
        System.out.println();
    }
}
