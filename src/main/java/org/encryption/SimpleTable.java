package org.encryption;

import java.util.ArrayList;
import java.util.List;

public class SimpleTable {
    public static final int COlUMN_CONFIG = 0;
    public static final int ROW_CONFIG = 0;
    private final List<String> table = new ArrayList<>();

    public SimpleTable(String text) {
        String textForTable = processText(text);

        List<Integer> tableConfig = findTableConfiguration(textForTable.length());
        int rows = tableConfig.get(ROW_CONFIG);
        int columns = tableConfig.get(COlUMN_CONFIG);



        fillTable(textForTable, columns, rows);
    }

    private String processText(String text) {
        String processedText = text;
        processedText = processedText.replaceAll("\\p{Punct}", "");
        processedText = processedText.replaceAll("\\s", "");

        return processedText;
    }


    private void fillTable(String text, int columns, int rows) {
       int initTextIter = 0;
       for (int rowIter = 0; rowIter < rows; rowIter++) {
           StringBuilder row = new StringBuilder();

           for (int columnIter = 0; columnIter < columns; columnIter++) {
               if(initTextIter >= text.length()) {
                   break;
               }
               row.append(text.charAt(initTextIter++));
           }

           table.add(row.toString());
           fillRow(columns);
       }
    }


    private void fillRow(int neededSize) {
        StringBuilder row = new StringBuilder(table.get(table.size() - 1));
        int delta = neededSize - row.length();

        while (delta-- > 0) {
            row.append(table.get(0).charAt(delta));
        }
        table.set(table.size() - 1, row.toString());
    }

    private static List<Integer> findTableConfiguration(int target) {
            for (int num1 = (int) Math.sqrt(target); num1 <= target; num1++) {
                int num2;
                double t = (double) target / num1;
                if (t < (double) num1) {
                    num2 = (int) Math.ceil(t);
                    System.out.println("Исходный ключ: " + num1 + " - " + num2);
                    return List.of(num1, num2);
                }
            }
         return List.of(1, target);
     }

    private String tryToBuildString(String code, int columns, int rows) {
        if(columns == 0 || rows == 0) return "";

        StringBuilder initialText = new StringBuilder();

        for(int rowIter  = 0; rowIter < rows; rowIter++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int initTextIter = rowIter; initTextIter < code.length(); initTextIter += columns) {
                stringBuilder.append(code.charAt(initTextIter));
            }
            initialText.append(stringBuilder);
        }

        return initialText.toString();
    }

    public String decode(String code, String etalon) {

        for (int iter1 = 1; iter1 < code.length(); iter1++) {
            for (int iter2 = 1; iter2*iter1 <= code.length(); iter2++) {
                String initTExt = tryToBuildString(code, iter1, iter2);
                if (initTExt.contains(processText(etalon))) {
                    System.out.println("Подобранный ключ: " + iter1 + " - " + iter2);
                    return initTExt;
                }
            }
        }

        return "";
    }
    public String encode() {

        return readColumns();

    }

    private String readColumns() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int columnIter = 0; columnIter < table.get(0).length(); columnIter++) {
            for (String string : table) {
                stringBuilder.append(string.charAt(columnIter));
            }
        }

        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (String s : table) {
            builder.append(s).append('\n');
        }

        return builder.toString();

    }
}

//    public SimpleTable(String text, int columns, int rows) {
//        String textForTable = processText(text);
//
//        fillTable(textForTable, columns, rows);
//
//    }

