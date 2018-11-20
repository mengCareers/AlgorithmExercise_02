package Test;

import java.util.Arrays;

public class TestArrToString {
    public static void main(String[] args) {
        boolean[] arr = {true, false, true};
        System.out.println(arr.toString());
        System.out.println(Arrays.toString(arr));

        char[] charr = {'A', 'B', 'C'};
        System.out.println(Arrays.toString(charr));
    }
}
