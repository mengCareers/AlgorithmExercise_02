package Amazon;

import java.util.*;

/*
S non-dup
permute chars of T so that T match the orders of S
 */
/*
we can give char of S a grade, the fronter the smaller
e.g. S = "cba"
Map:
a 2
b 1
c 0
T = "abcb"
     ||||
     2101

cbba
ch of T if not is Map, safe
or else,
 */
public class CustomSortString {
    public String customSortString(String S, String T) {

        Map<Character, Integer> charToGrade = buildMapOfS(S);
        PriorityQueue<Element> priorityQueue = new PriorityQueue<>(new Comparator<Element>() {
            @Override
            public int compare(Element o1, Element o2) {
                return o1.grade - o2.grade;
            }
        });
        String extraChars = getExtraCharsOfT(T, charToGrade, priorityQueue);
        StringBuilder result = new StringBuilder();
        result.append(reconstructCleanT(priorityQueue))
                .append(extraChars);

        return result.toString();
    }

    private char[] reconstructCleanT(PriorityQueue<Element> priorityQueue) {
        char[] result = new char[priorityQueue.size()];
        int ri = 0;
        while (!priorityQueue.isEmpty()) {
            Element curElement = priorityQueue.poll();
            result[ri++] = curElement.val;
        }
        return result;
    }

    private String getExtraCharsOfT(String t, Map<Character, Integer> charToGrade, PriorityQueue<Element> priorityQueue) {
        char[] chArr = t.toCharArray();
        StringBuilder extraChars = new StringBuilder();
        for (char c : chArr) {
            if (charToGrade.containsKey(c)) {
                Element element = new Element(c, charToGrade.get(c));
                priorityQueue.add(element);
            } else {
                extraChars.append(c);
            }
        }
        return extraChars.toString();
    }

    private Map<Character, Integer> buildMapOfS(String S) {
        Map<Character, Integer> charToGrade = new HashMap<>();
        char[] chArr = S.toCharArray();
        int grade = 0;
        for (char c : chArr) {
            charToGrade.put(c, grade++);
        }
        return charToGrade;
    }

    class Element {
        char val;
        int grade;

        public Element(char val, int grade) {
            this.val = val;
            this.grade = grade;
        }
    }
}
