package CompanyOriented.Visa;

public class Pseudo {
    public static void main(String[] args) {
        int ans = add(2437, 875);
        System.out.println(ans);
    }

    private static int add(int x, int y) {
        while (x != y) {
            if (x > y)
                x = x - y;
            if (x < y)
                y = y - x;
        }
        return x;
    }
}
