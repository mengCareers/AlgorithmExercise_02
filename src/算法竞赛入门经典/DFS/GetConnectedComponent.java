package 算法竞赛入门经典.DFS;

public class GetConnectedComponent {

    boolean[] visited;

    public int countConnectedComponent(int[][] graph) {
        int n = graph.length, countConnected = 0;
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                isConnectedComponentFrom(i, graph);
                countConnected++;
            }
        }
        return countConnected;
    }

    public boolean isConnectedComponent(int[][] graph) {
        int n = graph.length;
        visited = new boolean[n];
        isConnectedComponentFrom(0, graph);
        for (boolean val : visited) {
            if (!val) {
                return false;
            }
        }
        return true;
    }

    private void isConnectedComponentFrom(int vertex, int[][] graph) {
        visited[vertex] = true;
        for (int neighbourVertex : graph[vertex]) {
            if (!visited[neighbourVertex]) {
                isConnectedComponentFrom(neighbourVertex, graph);
            }
        }
    }

}
