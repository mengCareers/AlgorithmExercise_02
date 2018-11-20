package CompanyOriented.Amazon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * n courses to take, labeled from 0 to n - 1
 * to represent prerequisites, [0, 1] as 0's prerequisite is 1, i.e., take as 1, 0
 * return the ordering of courses to finish all
 * null if cannot finish all
 */
/*
there is dependency among nodes (courses) like topological sort
to represent graph, we need number of prerequisites (indegree), and list of courses take node as prerequisite (outlist)
 */
public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        int[] indegree = new int[numCourses];
        List<List<Integer>> outlist = new ArrayList<>();
        for (int i = 0; i < numCourses; i++)
            outlist.add(new ArrayList<>());
        for (int[] pair : prerequisites) {
            indegree[pair[0]]++;
            outlist.get(pair[1]).add(pair[0]);
        }

        return bfsTopological(indegree, outlist);
    }

    private int[] bfsTopological(int[] indegree, List<List<Integer>> outlist) {

        int[] result = new int[indegree.length];
        int ri = 0;

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int curNode = queue.poll();
            result[ri++] = curNode;
            for (int adjNode : outlist.get(curNode)) {
                indegree[adjNode]--;
                if (indegree[adjNode] == 0) {
                    queue.offer(adjNode);
                }
            }
        }

        return (result.length == indegree.length) ? result : null;
    }
}
