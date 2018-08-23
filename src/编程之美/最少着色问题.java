package 编程之美;

import java.util.Arrays;
import java.util.Comparator;

/**
 * input: graph[][]
 * output: ways to color the graph accordingly
 */
public class 最少着色问题 {

    public static void main(String[] args) {

        最少着色问题 inst = new 最少着色问题();

        int[][] graph = new int[][]{
                {1, 1, 1, 1, 0, 0},
                {1, 1, 1, 1, 1, 0},
                {1, 1, 1, 1, 0, 0},
                {1, 1, 1, 1, 1, 0},
                {0, 1, 0, 1, 1, 1},
                {0, 0, 0, 0, 1, 1}};
        System.out.println("ways to colorGraph:");
        inst.colorGraph(graph);

        int[][] section = {{1, 5}, {2, 3}, {3, 4}, {3, 6}};

        int ans = inst.colorSectionGraph(section);
        System.out.println("colorSectionGraph: " + ans);

        ans = inst.colorSectionGraphWisely(section);
        System.out.println("colorSectionGraphWisely: " + ans);
    }

    public int colorSectionGraphWisely(int[][] sections) {

        int n = sections.length, pi = 0, curTotalPoints = 0, maxCurTotalPoints = 0;
        Point[] points = new Point[n * 2];

        for (int[] section : sections) {
            points[pi++] = new Point(section[0], true);
            points[pi++] = new Point(section[1], false);
        }
        Arrays.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.val - o2.val;
            }
        });

        for (Point curPoint : points) {
            if (curPoint.isStart) {
                curTotalPoints++;
                maxCurTotalPoints = Math.max(maxCurTotalPoints, curTotalPoints);
            } else {
                curTotalPoints--;
            }
        }

        return maxCurTotalPoints;
    }

    class Point {
        int val;
        boolean isStart; // true if start

        public Point(int val, boolean isStart) {
            this.val = val;
            this.isStart = isStart;
        }
    }

    /*
    section[i] = [iStart, iEnd]
    node i and node j are connected if section[i] and section[j] overlapped
    we can utilize colorGraph()
    or we can colorSectionGraph() using Greedy
     */
    public int colorSectionGraph(int[][] section) {
        Arrays.sort(section, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int maxColors = 1, n = section.length;
        int[] curColorNo = new int[n];
        Arrays.fill(curColorNo, Integer.MAX_VALUE);
        boolean[] hasConflict;

        for (int si = 0; si < n; si++) {
            hasConflict = new boolean[maxColors];
            for (int sj = 0; sj < si; sj++) {
                if (isConflict(section[sj], section[si])) {
                    hasConflict[curColorNo[sj]] = true;
                }
            }
            int colorNo = 0;
            for (; colorNo < maxColors; colorNo++) {
                if (!hasConflict[colorNo]) {
                    break;
                }
            }
            if (colorNo < maxColors) {
                curColorNo[si] = colorNo;
            } else {
                curColorNo[si] = maxColors++;
            }
        }

        return maxColors;
    }

    private boolean isConflict(int[] section1, int[] section2) {
        if (section1[1] > section2[0]) {
            return true;
        }
        return false;
    }


    public void colorGraph(int[][] graph) {
        setColor(graph, 0, 0, new int[graph.length]);
    }

    private boolean checkColor(int[][] graph, int i, int curColorNo, int[] waysToColor) {
        for (int j = 0; j < i; j++) {
            if (graph[i][j] == 1 && waysToColor[j] == curColorNo) {
                return false;
            }
        }
        return true;
    }

    private void setColor(int[][] graph, int i, int colorNo, int[] waysToColor) {
        boolean isEnough = false;
        if (i == graph.length) {
            for (int wi = 0; wi < waysToColor.length; wi++) {
                System.out.print(waysToColor[wi] + ", ");
            }
            System.out.println();
            return;
        }
        for (int tmp = 0; tmp <= colorNo; tmp++) {
            if (checkColor(graph, i, tmp, waysToColor)) {
                isEnough = true;
                waysToColor[i] = tmp;
                setColor(graph, i + 1, colorNo, waysToColor);
            }
        }
        if (!isEnough) {
            colorNo++;
            waysToColor[i] = colorNo;
            setColor(graph, i + 1, colorNo, waysToColor);
        }
    }
}
