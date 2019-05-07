package Array;

public class KClosestPointsToOrigin {
    public static void main(String[] args) {
        KClosestPointsToOrigin inst = new KClosestPointsToOrigin();
        int[][] points = {{1, 3}, {-2, 2}};
        inst.kClosest(points, 1);
    }

    public int[][] kClosest(int[][] points, int K) {
        int pointsLen = points.length;
        // distances[i][0] is distance, distances[i][1] is index in points
        int[][] distances = new int[pointsLen][2];

        // Get all distances
        for (int i = 0; i < pointsLen; i++) {
            int distance = getDistance(points[i]);
            distances[i][0] = distance;
            distances[i][1] = i;
        }

        quickSelect(K, distances, 0, pointsLen - 1);

        // Get first K
        int[][] result = new int[K][2];
        for (int i = 0; i < K; i++) {
            result[i] = points[distances[i][1]];
        }

        return result;
    }

    // Find smallest K distances
    private void quickSelect(int K, int[][] distances, int start, int end) {
        if (start >= end) return;

        // Choose the element at start as pivot.
        int pivot = distances[start][0];
        // Put pivot into right position wall
        int wall = end + 1;
        for (int i = end; i > start; i--) {
            if (distances[i][0] >= pivot) {
                wall--;
                swap(i, wall, distances);
            }
        }
        wall--;
        swap(start, wall, distances);

        if (wall + 1 == K) return;
        else if (wall + 1 < K) quickSelect(K, distances, wall + 1, end);
        else quickSelect(K, distances, start, wall - 1);
    }

    private int getDistance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    private void swap(int i, int j, int[][] distances) {
        int[] temp = distances[i];
        distances[i] = distances[j];
        distances[j] = temp;
    }
}
