package Greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * partition S into as many parts as possible
 * such that each letter appears in at most one part
 * output: list of integers representing the size of these parts
 * # Consider the first label, say it's 'a'. The first partition must include it, and also the last occurrence of 'a'
 */
public class PartitionLabels {
    public static void main(String[] args) {
        PartitionLabels inst = new PartitionLabels();
        List<Integer> result = inst.partitionLabels("caedbdedda");
        System.out.println(result);
    }

    public List<Integer> partitionLabels(String S) {
        List<Integer> result = new ArrayList<>();
        int i = 0, partitionEnd = 0;
        while (i < S.length()) {
            partitionEnd = getLastOccur(S.charAt(i), S);
            partitionEnd = expandRange(i, partitionEnd, S);
            result.add(partitionEnd - i + 1);
            i = partitionEnd + 1;
        }
        return result;
    }

    private int expandRange(int s, int e, String S) {
        int newE = e;
        for (int i = s + 1; i < newE; i++) {
            if (getLastOccur(S.charAt(i), S) > newE) {
                newE = getLastOccur(S.charAt(i), S);
            }
        }
        return newE;
    }

    private int getLastOccur(char ch, String S) {
        for (int i = S.length() - 1; i >= 0; i--) {
            if (S.charAt(i) == ch) {
                return i;
            }
        }
        return -1;
    }
}
