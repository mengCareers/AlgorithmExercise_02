package CompanyOriented.Karat;

import java.util.*;

public class TaskByLevel {
    /*
           cook - eat
    sleep - study -|
     */
    public static void main(String[] args) {
        TaskByLevel inst = new TaskByLevel();
        String[][] tasks = {{"cook", "eat"}, {"study", "eat"}, {"sleep", "study"}};
        List<List<String>> result = inst.taskSchedule(tasks);
        System.out.println(result);
    }

    public List<List<String>> taskSchedule(String[][] tasks) {

        List<List<String>> result = new ArrayList<>();
        Map<String, Integer> inDegree = new HashMap<>();
        Map<String, Set<String>> outList = new HashMap<>();

        for (String[] task : tasks) {
            String u = task[0];
            String v = task[1];
            inDegree.put(v, inDegree.getOrDefault(v, 0) + 1);
            inDegree.put(u, inDegree.getOrDefault(u, 0));
            outList.putIfAbsent(u, new HashSet<>());
            outList.get(u).add(v);
        }

        List<String> level = new ArrayList<>();
        Deque<String> queue = new ArrayDeque<>();
        for (String node : inDegree.keySet()) {
            if (inDegree.get(node) == 0) {
                queue.offer(node);
                level.add(node);
            }
        }
        if (!level.isEmpty())
            result.add(new ArrayList<>(level));

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            level.clear();
            for (int i = 0; i < levelSize; i++) {
                String node = queue.poll();
                if (!outList.containsKey(node))
                    continue;
                for (String neighbour : outList.get(node)) {
                    inDegree.put(neighbour, inDegree.get(neighbour) - 1);
                    if (inDegree.get(neighbour) == 0) {
                        queue.offer(neighbour);
                        level.add(neighbour);
                    }
                }
            }
            if (!level.isEmpty())
                result.add(new ArrayList<>(level));
        }

        return result;
    }
}
