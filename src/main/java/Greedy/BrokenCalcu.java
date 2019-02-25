package Greedy;

public class BrokenCalcu {
    public int brokenCalc(int X, int Y) {
        if (X > Y) return X - Y;
        int times = 0;
        while (X * 2 <= Y) {
            X *= 2;
            times++;
        }
        times += Y - X;
        return times;
    }

    public static void main(String[] args) {
        BrokenCalcu inst = new BrokenCalcu();
        System.out.println(inst.brokenCalc(2, 3));
    }
}
