package CompanyOriented.Karat;

import java.util.*;

public class CourseSchedule {

    public static void main(String[] args) {
        CourseSchedule courseSchedule = new CourseSchedule();
        List<String> courses = new ArrayList<>(Arrays.asList("B,A", "C,B", "D,C"));
        System.out.println(courseSchedule.schedule(courses));
    }

    public String schedule(List<String> courses) {
        Map<String, List<String>> outList = new HashMap<>(); // Key: course, value: next courses.
        Map<String, Integer> indegree = new HashMap<>(); // Key: course, value: indegree
        Set<String> courseSet = new HashSet<>(), nextCourseSet = new HashSet<>();
        String[] splits = null;
        String curr, next;
        for (String coursePair : courses) {
            splits = coursePair.split(",");
            curr = splits[1];
            next = splits[0];
            outList.putIfAbsent(curr, new ArrayList<>());
            outList.get(curr).add(next);
            indegree.put(curr, indegree.getOrDefault(curr, 0));
            indegree.put(next, indegree.getOrDefault(next, 0) + 1);
        }
        Queue<String> queue = new LinkedList<>();
        // Visited
        for (Map.Entry<String, Integer> entry : indegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }
        int n = (courses.size() + 1) / 2; // The number of courses.
        while (!queue.isEmpty()) {
            String course = queue.poll();
            n--;
            if (n == -1) {
                return course;
            }
            if (!outList.containsKey(course))
                continue;
            for (String adj : outList.get(course)) {
                indegree.put(adj, indegree.get(adj) - 1);
                if (indegree.get(adj) == 0) {
                    queue.offer(adj);
                }
            }

        }
        return "";
    }

    public String schedule0(List<String> courses) {
        Map<String, String> map = new HashMap<>(); // Key: course, value: next course.
        Set<String> courseSet = new HashSet<>(), nextCourseSet = new HashSet<>();
        String[] splits = null;
        for (String coursePair : courses) {
            splits = coursePair.split(",");
            map.put(splits[1], splits[0]);
            courseSet.add(splits[1]);
            courseSet.add(splits[0]);
            nextCourseSet.add(splits[0]);
        }
        String key = ""; // Headcourse as default.
        for (String course : courseSet) {
            if (!nextCourseSet.contains(course)) {
                key = course;
                break;
            }
        }
        int n = (courses.size() + 1) / 2; // The number of courses.
        for (int i = 0; i < n; i++) {
            key = map.get(key);
        }
        return key;
    }

}
