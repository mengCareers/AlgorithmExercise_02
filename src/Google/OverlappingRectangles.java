package Google;

/**
 * input: two rectangles (bottom-left and up-right)
 * output:true if overlap
 */
public class OverlappingRectangles {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return !(rec1[1] >= rec2[3] // higher
                || rec1[3] <= rec2[1] // lower
                || rec1[2] <= rec2[0] // lefter
                || rec1[0] >= rec2[2]);// righter
    }
}
