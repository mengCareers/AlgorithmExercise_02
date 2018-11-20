package CompanyOriented.Karat;

import java.util.*;

public class TaskByLevel {

    public static void main(String[] args) {
        TaskByLevel inst = new TaskByLevel();
        String[][] tasks = {{"cook", "eat"}, {"study", "eat"}, {"sleep", "study"}};
        List<List<String>> result = inst.taskByLevel(tasks);
        System.out.println(result);
    }

    public List<List<String>> taskByLevel(String[][] edges) {

        Map<String, Integer> inDegree = new HashMap<>();
        Map<String, Set<String>> outList = new HashMap<>();

        for (String[] edge : edges) {
            String u = edge[0];
            String v = edge[1];
            inDegree.put(v, inDegree.getOrDefault(v, 0) + 1);
            inDegree.put(u, inDegree.getOrDefault(u, 0));
            outList.putIfAbsent(u, new HashSet<>());
            outList.putIfAbsent(v, new HashSet<>());
            outList.get(u).add(v);
        }

        Queue<String> queue = new LinkedList<>();

        for (Map.Entry<String, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }

        List<List<String>> result = new ArrayList<>();
        List<String> curResult;
        String curr;

        while (!queue.isEmpty()) {
            int size = queue.size();
            curResult = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                curr = queue.poll();
                curResult.add(curr);
                for (String adj : outList.get(curr)) {
                    inDegree.put(adj, inDegree.get(adj) - 1);
                    if (inDegree.get(adj) == 0) {
                        queue.offer(adj);
                    }
                }
            }
            result.add(curResult);
        }
        return result;
    }

}
