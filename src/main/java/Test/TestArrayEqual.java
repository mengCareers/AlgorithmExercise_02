package Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestArrayEqual {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        int[] arr1 = {1, 2, 3};
        map.put(Arrays.toString(arr1), 1);
        int[] arr2 = {1, 2, 3};
        System.out.println(map.getOrDefault(Arrays.toString(arr2), 0));
    }
}
