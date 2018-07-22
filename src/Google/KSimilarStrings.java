package Google;

import java.util.*;

/*
input: A, B
output: minimum swaps to make A equal to B
test case:
    A = abc, B = bca, 2
    A = abac, B = baca, 2
thinking process: shortest distance, BFS,
    LET'S BUILD THE GRAPH FIRST, nodes: S, edges exist between nodes can be transformed within 1 step
    start point: S
    goal: B
    transition: standing at A[i], its neighbours: all values it can get within one step
*/
public class KSimilarStrings {
    public int kSimilarity(String A, String B) {

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(A);
        visited.add(A);
        int level = 0;

        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                String curNode = queue.poll();
                if (curNode.equals(B)) {
                    return level;
                }
                for (String neighbour : getNeighbours(curNode, B)) {
                    if (!visited.contains(neighbour)) {
                        queue.offer(neighbour);
                        visited.add(neighbour);
                    }
                }
            }
            level++;
        }

        return -1;
    }

    private List<String> getNeighbours(String S, String B) {
        // only those swaps that can make A B differ less matter
        // that is, if A[i] != B[i] but A[j] == B[i], swap(A, i, j), perfect

        List<String> result = new ArrayList<>();
        char[] arr = S.toCharArray();

        int i = 0;
        for (; i < arr.length; i++) {
            if (arr[i] != B.charAt(i)) {
                break;
            }
        }

        for (int j = i + 1; j < arr.length; j++) {
            if (arr[j] == B.charAt(i)) {
                swap(arr, i, j);
                result.add(new String(arr));
                swap(arr, i, j);
            }
        }

        return result;
    }

    private void swap(char[] arr, int i, int j) {

        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
