package Contest;

import java.util.*;

public class BitwiseORsOfSubarrays {

    public static void main(String[] args) {
        BitwiseORsOfSubarrays inst = new BitwiseORsOfSubarrays();
        int[] A = {1, 2, 4};
        inst.subarrayBitwiseORs(A);
    }

    public int subarrayBitwiseORs(int[] A) {

        Arrays.sort(A);
        List<List<Integer>> subsets = getSubsets(A);
        Set<Integer> result = new HashSet<>();
        for (List<Integer> subset : subsets) {
            if (getOR(subset) == 5)
                System.out.println(1 | 4);
            //result.add();
        }

        return result.size();
    }

    private int getOR(List<Integer> list) {

        int result = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            result |= list.get(i);
        }
        return result;
    }

    private List<List<Integer>> getSubsets(int[] A) {

        List<List<Integer>> result = new ArrayList<>();
        getSubsetsFrom(A, 0, result, new ArrayList<>());
        return result;
    }

    private void getSubsetsFrom(int[] A, int curId, List<List<Integer>> result, List<Integer> temp) {

        if (temp.size() != 0)
            result.add(new ArrayList<>(temp));

        for (int i = curId; i < A.length; i++) {
            if (i - 1 >= curId && A[i - 1] == A[i]) {
                continue;
            }
            temp.add(A[i]);
            getSubsetsFrom(A, i + 1, result, temp); // 1 2 3
            temp.remove(temp.size() - 1);
        }
    }
}
