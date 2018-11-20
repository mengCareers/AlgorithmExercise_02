package 算法图解;

/*
input: l, w一块地
 均匀地分成方块，且分出的方块要尽可能大
output: 方块边长

 */
/*
we find base case first:
if l = 2 * w, split simple
 */
public class 均分方块 {
    public static void main(String[] args) {
        均分方块 inst = new 均分方块();
        int edge = inst.getEdge(90, 30);
        System.out.println(edge);
    }

    public int getEdge(int l, int w) {

        if (l % w == 0 || w % l == 0) {
            return Math.min(l, w);
        }

        int curEdge = Math.min(w, l);
        if (l > w) {
            while (l - curEdge > 0) {
                l -= curEdge;
            }
        } else {
            while (w - curEdge > 0) {
                w -= curEdge;
            }
        }

        return getEdge(l, w);
    }
}
