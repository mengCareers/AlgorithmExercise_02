package Contest;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * - It seems that you intend to use DP.
 * Yes, take {3, 2, 2, 1} for example,
 * we sort them first, {1, 2, 2, 3},
 * and I also think of Two-pointers.
 * 1 2 2 3
 * i     j
 * if (p[i] + p[j] > limit), we can either abort i or j (abort means he needs a boat to rescue him only)
 * - I could understand why we decide to abort j, for we cannot pair another with him.
 * - But why should we consider aborting i?
 * It is possible that we abort i, and we get a better result.
 * Take limit = 3 for example,
 * 1 + 3 > limit,
 * - Then 2 + 3 > limit surely. Hou could that be better?
 */
public class BoatsToSavePeople {
    public static void main(String[] args) {
        BoatsToSavePeople inst = new BoatsToSavePeople();
        int[] people = {1, 2, 4, 5};
        int limit = 6;
        System.out.println(inst.numRescueBoats(people, limit));
    }

    public int numRescueBoats(int[] people, int limit) {

        Arrays.sort(people);
        return rescue(people, limit, 0, people.length - 1);
    }

    private int rescue(int[] people, int limit, int lo, int hi) {

        if (lo == hi) {
            return 1;
        }

        if (lo + 1 == hi) {
            if (people[lo] + people[hi] <= limit) {
                return 1;
            }
            return 2;
        }

        if (people[lo] + people[hi] <= limit) {
            return 1 + rescue(people, limit, lo + 1, hi - 1);
        }

        return 1 + rescue(people, limit, lo, hi - 1);
    }
}
