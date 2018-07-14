package Facebook;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * input: ages[],
 * A not fr B if ..
 * or else, A fr B
 * output: # of fr
 * e.g. 16, 17, 18
 * 17 -> 15,18 -> 17
 */
/*
we sort them, and A starts with the biggest
 */

/**
 * MENTOR:
 * For each pair (ageA, countA), (ageB, countB), if the conditions are satisfied with respect to age,
 * then countA * countB pairs of people made friend requests.
 */
public class FriendsOfAppropriateAges {
    public int numFriendRequests(int[] ages) {
        int cntFriendRequests = 0;
        int[] map = new int[121]; // 1 ~ 120;
        for (int age : ages) {
            map[age]++;
        }
        for (int A = 1; A <= 120; A++) {
            for (int B = 1; B <= 120; B++) {
                if (ages[B] <= 0.5 * ages[A] + 7) continue;
                if (ages[B] > ages[A]) continue;
                if (ages[B] > 100 && ages[A] < 100) continue;
                cntFriendRequests += map[A] * map[B];
                if (A == B) {
                    cntFriendRequests -= map[A];
                }
            }
        }
        return cntFriendRequests;
    }

}
