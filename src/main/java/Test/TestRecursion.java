package Test;

public class TestRecursion {
    public static void main(String[] args) {
        System.out.println(recur(3));
        System.out.println(itera(3));
    }

    public static int recurTail(int i) {
        if (i == 0) {
            return i;
        }
        return recurTail(i - 1);
    }


    public static int recur(int i) {
        if (i == 0) {
            return i;
        }
        return 3 + recur(i - 1);
    }


    public static int recur(int i, int sum) {
        if (i == 0) {
            return sum;
        }
        return recur(i - 1, sum + 3);
    }

    public static int itera(int i) {
        int sum = 0;
        while (i != 0) {
            i--;
            sum += 3;
        }
        return sum;
    }
}
