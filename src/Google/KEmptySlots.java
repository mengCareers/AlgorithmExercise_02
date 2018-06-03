package Google;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * input : flowers[i] = p, flower will bloom at day i at position p
 * <p>
 * output : which day there exists
 * EXIST two flowers bloom &&  # of flowers between them is k
 * MENTOR: THE AIM OF ISVALID() IS TO CHECK LOWER AND HIGHER NEIGHBORS OF LATEST BLOOMED FLOWERS,
 * IF WE CAN FIGURE OUT LOWER AND HIGHER NEIGHBORS OF LATEST BLOOMED FLOWERS
 * WE DON'T NEED THE LOOP.
 * HOW CAN WE SAVE LOWER AND HIGHER NEIGHBORS OF LATEST BLOOMED FLOWERS?
 * THERE IS A DATA STRUCTURE CALLED TREESET.
 * 0  1  2
 * 0 +
 * 1 +     +
 * 2 +  +  +
 */

public class KEmptySlots {

    public static void main(String[] args) {
        KEmptySlots inst = new KEmptySlots();
        int[] flowers = {1, 3, 2};
        int k = 1;
        System.out.println(inst.kEmptySlots(flowers, k));
    }


    public int kEmptySlots(int[] flowers, int k) {
        int N = flowers.length;
        TreeSet<Integer> posBlooming = new TreeSet<>();
        for (int day = 0; day < N; day++) {
            int posCur = flowers[day];
            posBlooming.add(posCur);
            if (isValid(posBlooming, posCur, k, N)) {
                return day + 1;
            }
        }
        return -1;
    }

    private boolean isValid(TreeSet<Integer> posBlooming, int posCur, int k, int N) {
        Integer left = posBlooming.lower(posCur);
        Integer right = posBlooming.higher(posCur);
        return ((left != null) && (posCur - left - 1 == k))
                || ((right != null) && (right - posCur - 1 == k));
    }

    public int kEmptySlotsTLE(int[] flowers, int k) {
        int N = flowers.length;
        Set<Integer> posBlooming = new HashSet<>();
        for (int day = 0; day < N; day++) {
            posBlooming.add(flowers[day] - 1);
            if (isValidTLE(posBlooming, k, N)) {
                return day + 1;
            }
        }
        return -1;
    }

    private boolean isValidTLE(Set<Integer> posBlooming, int k, int N) {
        int lastBlooming = -1;
        int distBetween = 0;
        for (int i = 0; i < N; i++) {
            if (posBlooming.contains(i)) {
                if (lastBlooming == -1) {
                    lastBlooming = i;
                } else {
                    distBetween = i - lastBlooming - 1;
                    if (distBetween != k) {
                        lastBlooming = i;
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

