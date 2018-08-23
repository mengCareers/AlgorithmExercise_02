package Amazon;

import java.util.*;

/*
[alphanumeric] list of words / list of integers
reorder, then
    identifier sorted in ASCII (num then letter)
    word-log, lexicographically sorted, if tie, identifier lexicographically sorted
    integer-log,

 */
public class ReorderLogLines {

    public static void main(String[] args) {
        ReorderLogLines inst = new ReorderLogLines();
        String[] input = {
                "3a i love you",
                "e5 521",
                "2q4 thank you",
                "rd3 thank you",
                "3b 521"
        };
        List<String> result = inst.reorderLines(5, Arrays.asList(input));
        System.out.println(result);
    }

    public List<String> reorderLines(int logFileSize, List<String> logLines) {
        List<String> letterLines = new ArrayList<>();
        List<String> numberLines = new ArrayList<>();

        for (String line : logLines) {
            if (Character.isLetter(line.charAt(line.length() - 1))) {
                letterLines.add(line);
            } else {
                numberLines.add(line);
            }
        }

        Collections.sort(letterLines, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {

                String o1Content = o1.substring(o1.indexOf(' ') + 1);
                String o2Content = o2.substring(o2.indexOf(' ') + 1);
                if (o1Content.equals(o2Content)) {
                    String o1Id = o1.substring(0, o1.indexOf(' '));
                    String o2Id = o2.substring(0, o2.indexOf(' '));
                    return o1Id.compareTo(o2Id); // should be to ASCII naturally
                }
                return o1Content.compareTo(o2Content);
            }
        });

        List<String> result = new LinkedList<>();
        for (String line : letterLines) {
            result.add(line);
        }
        for (String line : numberLines) {
            result.add(line);
        }
        return result;
    }

}
