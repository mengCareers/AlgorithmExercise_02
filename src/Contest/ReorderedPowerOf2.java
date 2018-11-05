package Contest;

import java.util.*;

public class ReorderedPowerOf2 {
    public static void main(String[] args) {
        ReorderedPowerOf2 inst = new ReorderedPowerOf2();
        System.out.println(inst.reorderedPowerOf2(4609));
    }

    public boolean reorderedPowerOf2(int N) {
        if (isPowerOf2(N)) {
            return true;
        }

        String strN = String.valueOf(N);
        int[] arrN = new int[strN.length()];
        for (int i = 0; i < strN.length(); i++) {
            arrN[i] = strN.charAt(i) - '0';
        }
        Arrays.sort(arrN);

        List<String> reordered = new LinkedList<>();
        reorder(arrN, reordered, new StringBuilder(), new LinkedList<>());
        for (String tmp : reordered) {
            if (tmp.startsWith("0")) {
                continue;
            }
            if (isPowerOf2(Integer.valueOf(tmp))) {
                return true;
            }
        }

        return false;
    }

    private void reorder(int[] arrN, List<String> allRes, StringBuilder curRes, List<Integer> curIndices) {
        if (curRes.length() == arrN.length) {
            allRes.add(curRes.toString());
            return;
        }
        for (int i = 0; i < arrN.length; i++) {
            if (curIndices.contains(i)) {
                continue;
            }
            if (i > 0 && arrN[i] == arrN[i - 1] && !curIndices.contains(i - 1)) {
                continue;
            }
            curRes.append(arrN[i]);
            curIndices.add(i);
            reorder(arrN, allRes, curRes, curIndices);
            curRes.deleteCharAt(curRes.length() - 1);
            curIndices.remove(curIndices.size() - 1);
        }
    }

    private boolean isPowerOf2(int n) {
        return (n & (n - 1)) == 0;
    }
}
