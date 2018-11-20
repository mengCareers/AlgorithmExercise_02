package CompanyOriented.Amazon;

import java.util.*;

/**
 * forest:
 * 0, can't pass
 * 1, can pass
 * >1, can pass and cut a tree
 * BFS
 * start point: (0, 0)
 * aim point: all trees are cut
 * end point: all trees are cut or no way to go forward
 * transition: standing at (x, y) we can go 4 directions
 * if (within boundary of forest && unvisited && != 0 && if cut tree height should be increasing)
 * <p>
 * start state: curX, curY
 * end state: aimX, aimY or no way to go
 */
public class CutOffTreesForGolfEvent {
    public static void main(String[] args) {
        CutOffTreesForGolfEvent inst = new CutOffTreesForGolfEvent();
        List<List<Integer>> forest = new ArrayList<>();
        forest.add(Arrays.asList(54581641, 64080174, 24346381, 69107959));
        forest.add(Arrays.asList(86374198, 61363882, 68783324, 79706116));
        forest.add(Arrays.asList(668150, 92178815, 89819108, 94701471));
        forest.add(Arrays.asList(83920491, 22724204, 46281641, 47531096));
        forest.add(Arrays.asList(89078499, 18904913, 25462145, 60813308));
        inst.cutOffTree(forest);
    }

    private static int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int cutOffTree(List<List<Integer>> forest) {

        List<int[]> treeHeights = getAllTreeHights(forest);
        Collections.sort(treeHeights, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        int minSteps = 0;
        int curX = 0, curY = 0;
        while (!treeHeights.isEmpty()) {
            int[] curTree = treeHeights.remove(0);
            int steps = getMinimumSteps(forest, curX, curY, curTree[0], curTree[1]);
            if (steps == -1) {
                return -1;
            }
            minSteps += steps;
            curX = curTree[0];
            curY = curTree[1];
            forest.get(curX).set(curY, 1);
        }
        return minSteps;
    }

    private int getMinimumSteps(List<List<Integer>> forest, int curX, int curY, int aimX, int aimY) {

        int minSteps = 0;
        int rows = forest.size(), cols = forest.get(0).size();
        boolean[][] visited = new boolean[forest.size()][forest.get(0).size()];
        Queue<int[]> queue = new LinkedList<>();
        int startVal = forest.get(curX).get(curY);
        queue.offer(new int[]{curX, curY});
        visited[curX][curY] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curCell = queue.poll();
                if (curCell[0] == aimX && curCell[1] == aimY) {
                    return minSteps;
                }
                for (int[] direction : directions) {
                    int nx = curCell[0] + direction[0];
                    int ny = curCell[1] + direction[1];
                    if (nx >= 0 && nx < rows && ny >= 0 && ny < cols && !visited[nx][ny] && forest.get(nx).get(ny) != 0) {
                        queue.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
            minSteps++;
        }
        return -1;
    }

    private List<int[]> getAllTreeHights(List<List<Integer>> forest) {
        List<int[]> treeHeights = new LinkedList<>();
        for (int i = 0; i < forest.size(); i++) {
            for (int j = 0; j < forest.get(0).size(); j++) {
                int tmpVal = forest.get(i).get(j);
                if (tmpVal > 1) {
                    int[] element = new int[3];
                    element[0] = i;
                    element[1] = j;
                    element[2] = tmpVal;
                    treeHeights.add(element);
                }
            }
        }
        return treeHeights;
    }
}
