package Contest;

import java.util.*;

public class AdvantageShuffle {
    public int[] advantageCount(int[] A, int[] B) {

        Map<Integer, List<Integer>> valBToBiggerValA = new HashMap();
        for (int b : B) {
            valBToBiggerValA.put(b, new LinkedList<>());
        }
        Arrays.sort(A);
        int[] oriB = Arrays.copyOf(B, B.length);
        Arrays.sort(B);

        int Ai = 0, Bi = B.length - 1;
        for (int i = 0; i < B.length && Ai < A.length; i++) {
            while (Ai < A.length && A[Ai] <= B[i]) {
                valBToBiggerValA.get(B[Bi--]).add(A[Ai++]);
            }
            if (Ai >= A.length) {
                break;
            }
            valBToBiggerValA.get(B[i]).add(A[Ai++]);
        }

        int[] result = new int[oriB.length];
        int ri = 0;
        for (int b : oriB) {
            result[ri++] = valBToBiggerValA.get(b).get(0);
            valBToBiggerValA.get(b).remove(0);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] A = {2, 0, 4, 1, 2};
        int[] B = {1, 3, 0, 0, 2};
        AdvantageShuffle inst = new AdvantageShuffle();
        int[] ans = inst.advantageCount(A, B);
        for (int i : ans) {
            System.out.print(i + " ");
        }
    }

    public int[] advantageCountMLE(int[] A, int[] B) {
        List<int[]> permutations = new ArrayList<>();
        permute(A, 0, permutations);
        int maxAdvantage = -1;

        Map<Integer, Integer> advantageToIndex = new HashMap<>();
        for (int i = 0; i < permutations.size(); i++) {
            int[] tmp = permutations.get(i);
            int cntAdvantage = getAdvantage(tmp, B);
            if (cntAdvantage > maxAdvantage) {
                advantageToIndex.put(cntAdvantage, i);
                maxAdvantage = cntAdvantage;
            }
        }

        return maxAdvantage == -1 ? A : permutations.get(advantageToIndex.get(maxAdvantage));
    }

    private int getAdvantage(int[] a, int[] b) {
        int cnt = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > b[i]) {
                cnt++;
            }
        }
        return cnt;
    }

    private void permute(int[] arr, int curPos, List<int[]> permutations) {
        if (curPos == arr.length) {
            permutations.add(Arrays.copyOf(arr, arr.length));
            return;
        }

        for (int i = curPos; i < arr.length; i++) {
            int tmp = arr[curPos];
            arr[curPos] = arr[i];
            arr[i] = tmp;

            permute(arr, curPos + 1, permutations);

            arr[i] = arr[curPos];
            arr[curPos] = tmp;
        }
    }

}
