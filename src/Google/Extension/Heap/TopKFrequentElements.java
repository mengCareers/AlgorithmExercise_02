package Google.Extension.Heap;

import java.util.*;

public class TopKFrequentElements {
    public List<Integer> topKFrequent(int[] nums, int k) {

        if (nums == null || nums.length == 0)
            return null;

        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> map = buildMap(nums); // key: value of num, value: frequency of num
        PriorityQueue<Element> maxHeap = new PriorityQueue<>((a, b) -> (b.freq - a.freq));
        for (int key : map.keySet()) {
            maxHeap.offer(new Element(key, map.get(key)));
        }

        while (k-- > 0) { // we assume k is valid
            result.add(maxHeap.peek().val);
            maxHeap.poll();
        }

        return result;
    }

    private Map<Integer, Integer> buildMap(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        return map;
    }

    class Element {

        int val;
        int freq;

        public Element(int val, int freq) {
            this.val = val;
            this.freq = freq;
        }
    }
}
