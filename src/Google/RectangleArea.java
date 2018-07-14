package Google;

public class RectangleArea {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int sFirst = (C - A) * (D - B);
        int sSecond = (G - E) * (H - F);
        int left = Math.max(A, E);
        int right = Math.min(C, G);
        int bottom = Math.max(B, F);
        int up = Math.min(D, H);
        int sOverlap = 0;
        if ((left < right) && (bottom < up)) {
            sOverlap = (right - left) * (up - bottom);
        }
        return sFirst + sSecond - sOverlap;
    }
}
