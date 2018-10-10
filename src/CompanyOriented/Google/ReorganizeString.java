package CompanyOriented.Google;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class ReorganizeString {
    public static void main(String[] args) {
        ReorganizeString inst = new ReorganizeString();
        inst.reorganizeString("abaa");
    }

    public String reorganizeString(String S) {

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((o1, o2) -> (o2[1] - o1[1]));
        int[] map = new int[256]; // map[i] = (char)i frequency
        char[] array = S.toCharArray();
        for (char ch : array) {
            map[ch]++;
        }
        for (int i = 0; i < map.length; i++) {
            if (map[i] > 0) {
                pq.offer(new int[]{(char) i, map[i]});
            }
        }
        List<int[]> toPutBack = new ArrayList<>();
        StringBuilder result = new StringBuilder();

        while (!pq.isEmpty()) {

            toPutBack.clear();
            int i = 0;
            while (i <= 1) {
                int[] curElement = pq.poll();
                curElement[1]--;
                if (curElement[1] > 0) {
                    toPutBack.add(curElement);
                }
                i++;
                result.append((char) (curElement[0]));
                if (pq.isEmpty()) {
                    break;
                }
            }
            for (int[] tmp : toPutBack) {
                pq.offer(tmp);
            }
            if (i <= 1 && !pq.isEmpty()) {
                return "";
            }
        }

        return result.toString();
    }
}
