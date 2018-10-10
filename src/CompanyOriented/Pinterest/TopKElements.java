package CompanyOriented.Pinterest;

import java.util.*;

public class TopKElements {

    /*
    topKFrequentWords and output lexicographically
     */

    public List<String> topKFrequentWords(String[] words, int k) {

        int n = words.length;
        ArrayList<String> bucketResult, result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>(); // Key: word, value: frequency.
        ArrayList<String>[] buckets = new ArrayList[n + 1]; // For bucket sort.

        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        for (String word : map.keySet()) {
            int freq = map.get(word);
            if (buckets[freq] == null)
                buckets[freq] = new ArrayList<>();
            buckets[freq].add(word);
        }

        for (int i = n; i > 0; i--) {
            ArrayList<String> list = buckets[i];
            if (list != null) {
                bucketResult = new ArrayList<>();
                getWords(buildTrie(list), bucketResult);
                if (bucketResult.size() > k) {
                    for (int j = 0; j < k; j++)
                        result.add(bucketResult.get(j));
                    break;
                }
                k -= bucketResult.size();
                result.addAll(bucketResult);
                if (k == 0)
                    break;
            }
        }

        return result;
    }

    private void getWords(Node parentPtr, ArrayList<String> result) {

        if (parentPtr.content != null) {
            result.add(parentPtr.content);
        }

        for (Node child : parentPtr.children) {
            if (child != null) {
                getWords(child, result);
            }
        }

    }

    private Node buildTrie(ArrayList<String> list) {

        Node root = new Node();

        Node ptr;
        for (String word : list) {
            ptr = root;
            for (char ch : word.toCharArray()) {
                int order = ch - 'a';
                if (ptr.children[order] == null)
                    ptr.children[order] = new Node();
                ptr = ptr.children[order];
            }
            ptr.content = word;
        }

        return root;
    }

    class Node {

        Node[] children;
        String content;

        public Node() {
            children = new Node[26];
            content = null;
        }
    }

    /*
    topKFrequentNumbers
     */

    public List<Integer> topKFrequentNumbers(int[] nums, int k) {

        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>(); // Key: num, value: frequency.
        List<Integer> result = new ArrayList<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
/*
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>((a, b) -> Integer.compare(b.getValue(), a.getValue()));
        priorityQueue.addAll(map.entrySet());
        while (!priorityQueue.isEmpty() && result.size() < k) {
            Map.Entry<Integer, Integer> entry = priorityQueue.poll();
            result.add(entry.getKey());
        }
*/

        ArrayList<Integer>[] buckets = new ArrayList[n + 1];
        for (int mapKey : map.keySet()) {
            if (buckets[map.get(mapKey)] == null)
                buckets[map.get(mapKey)] = new ArrayList<>();
            buckets[map.get(mapKey)].add(mapKey);
        }
        for (int i = n; i > 0 && result.size() < k; i--) {
            ArrayList<Integer> numList = buckets[i];
            if (numList != null) {
                result.addAll(numList);
            }
        }

        return result;
    }
}
