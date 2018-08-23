package GoldmanSachs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
input: array of strings
output: 格式统一的表格，每一列右对齐，相邻两列间至少空一格
 */
/*
tp: A Comma-Separated Values (CSV) file
In a CSV file, normally there are two issues:

 */
public class FormatCSV {

    public static void main(String[] args) {
        String[] lines = {
                "a,b,c,,d",
                "aa,b,ccc,dd"
        };
        formatCSV(lines);
    }

    public static void formatCSV(String[] lines) {

        int countColumn = lines[0].split(",").length;
        String[] splits = null;
        StringBuilder curColumn = null;
        String tmpColumn = "";

        int[] columnLongest = new int[countColumn];
        for (String line : lines) {
            splits = line.split(","); // "a", "b", "c"
            for (int i = 0; i < splits.length; i++) {
                int curLength = splits[i].length();
                if (curLength > columnLongest[i]) {
                    columnLongest[i] = curLength;
                }
            }
        }

        for (String line : lines) {
            splits = line.split(",");
            for (int i = 0; i < splits.length; i++) {
                curColumn = new StringBuilder();
                curColumn.append(splits[i]);
                while (curColumn.length() < columnLongest[i]) {
                    curColumn.insert(0, " ");
                }
                System.out.print(curColumn);
            }
            System.out.println();
        }

    }

}
