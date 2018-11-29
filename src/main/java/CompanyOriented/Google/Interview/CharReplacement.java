package CompanyOriented.Google.Interview;

import java.util.*;

/**
 * Given string A and B, return true if B could be transferred from A through CharReplacement.
 * CharReplacement is to replace same chars with a char in B.
 * <p>
 * #1 Do we need to replace from left to right?
 * #2 Can we use characters out of chars in B?
 * <p>
 * #1 Do we need to replace from left to right?
 * #2 Can we use characters out of chars in B?
 * <p>
 * #1 Do we need to replace from left to right?
 * #2 Can we use characters out of chars in B?
 * <p>
 * #1 Do we need to replace from left to right?
 * #2 Can we use characters out of chars in B?
 */

/**
 * #1 Do we need to replace from left to right?
 * #2 Can we use characters out of chars in B?
 */

public class CharReplacement {

    /**
     * Solution0: #1 Yes #2 No
     * 1 No two different chars in B matches the same char in A for each replacement.
     * 2 There is not cycle in mappings graph - key is u in A, value is v in B.
     * 3 No char occurred in B but occurs in A.l
     * <p>
     * We can mock each replacement and check above rule 1.
     */

    public static boolean solution0(String A, String B) {
        // Corner cases to add.

        char[] arrA = A.toCharArray(), arrB = B.toCharArray();

        while (!Arrays.equals(arrA, arrB)) {
            replaceFirstDiffInA(arrA, arrB);
            if (!isTwoDiffChBMapDiffChA(arrA, arrB))
                return false;
        }

        return true;
    }

    private static void replaceFirstDiffInA(char[] arrA, char[] arrB) {
        int n = arrA.length, i = 0;

        while (i < n && arrA[i] == arrB[i])
            i++;

        for (int j = i; j < n; j++) {
            if (arrA[j] == arrA[i])
                arrA[j] = arrB[i];
        }
    }

    private static boolean isTwoDiffChBMapDiffChA(char[] arrA, char[] arrB) {
        int n = arrA.length;

        int[] adjListA = new int[256];
        for (int i = 0; i < n; i++) {
            char chA = arrA[i], chB = arrB[i];
            if (adjListA[chA] == 0)
                adjListA[chA] = chB;
            if (adjListA[chA] != chB)
                return false;
        }

        return true;
    }

    /**
     * Solution1: #1 Yes #2 No
     * 1 No two different chars in B matches the same char in A for each replacement.
     * 2 There is not cycle in mappings graph - key is u in A, value is v in B.
     * 3 No char occurred in B but occurs in A.
     * <p>
     * Use a map to save mappings and check each rule above.
     */

    public static boolean canTransferByCharReplacement(String A, String B) {
        Map<Character, Character> map = new HashMap<>(); // Key: char in A, value: its corresponding char in B
        Set<Character> visitedValues = new HashSet<>();
        int n = A.length();

        for (int i = 0; i < n; i++) {
            char chA = A.charAt(i), chB = B.charAt(i);

            /* Need no replacement: a - b, a - b. */
            if (map.containsKey(chA) && map.get(chA) == chB)
                continue;

            /* Char occurred in B but occurs in A:  a - b, b - c. */
            if (!map.containsKey(chA) && visitedValues.contains(chA)) {
                return false;
            }

            /* Two different chars in B matches the same char in A: a - b, a - c. */
            if (map.containsKey(chA) && map.get(chA) != chB) {
                return false;
            }

            /* Cycle: a - b, b - a. */
            if (map.containsKey(chB) && map.get(chB) == chA) {
                return false;
            }

            map.put(chA, chB);
            visitedValues.add(chB);
        }

        return true;
    }

    /**
     * Solution2: #1 No #2 No
     * 1 No two different chars in B matches the same char in A for each replacement.
     * 2 There is not cycle in mappings graph - key is u in A, value is v in B.
     * <p>
     * Detected cycle in directed graph - topological sort.
     */

    public static boolean solution2(String A, String B) {

        /* Build graph. */
        Map<Character, Character> adjList = new HashMap<>(); // Key: char in A, value: its corresponding char in B.
        Map<Character, Integer> inDegree = new HashMap<>();
        if (!buildGraph(A, B, adjList, inDegree))
            return false;

        /* Topological sort is used to detect cycle in directed graph. */
        Queue<Character> queue = new LinkedList<>();
        for (Map.Entry<Character, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }

        int polledNum = 0;
        while (!queue.isEmpty()) {
            char u = queue.poll();
            polledNum++;
            if (!adjList.containsKey(u))
                continue;
            char v = adjList.get(u);
            inDegree.put(v, inDegree.get(v) - 1);
            if (inDegree.get(v) == 0)
                queue.offer(v);
        }

        return (polledNum != inDegree.size());
    }

    // Return false if !isTwoDiffChBMapDiffChA.
    private static boolean buildGraph(String A, String B, Map<Character, Character> adjList, Map<Character, Integer> inDegree) {
        for (int i = 0; i < A.length(); i++) {
            char u = A.charAt(i);
            char v = B.charAt(i);
            if (adjList.containsKey(u)) {
                if (adjList.get(u) != v) {
                    return false;
                }
            } else {
                adjList.put(u, v);
                if (u != v)
                    inDegree.put(v, inDegree.getOrDefault(v, 0) + 1);
                inDegree.put(u, inDegree.getOrDefault(u, 0));
            }
        }
        return true;
    }


}
