package Amazon;

import java.util.Scanner;

public class Util找规律 {
    private static void dist(String a, String b) {
        char[] arrA = a.toCharArray();
        char[] arrB = b.toCharArray();
        for (int i = 0; i < arrA.length; i++) {
            int dist = arrB[i] - arrA[i];
            System.out.print(dist);
            System.out.print(", ");
        }
        System.out.println();
    }

    private static void stringToNo(String ori) {
        char[] arr = ori.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] - 'A' + 1);
            System.out.print(", ");
        }
        System.out.println();
    }

    private static String noToString(int[] nums) {
        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            int ascii = num - 1 + (int) 'A';
            System.out.print((char) ascii);
            sb.append((char) ascii);
            System.out.print(", ");
        }
        System.out.println();
        return sb.toString();
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("A = ");
        String A = sc.nextLine();
        stringToNo(A);

        System.out.print("B = ");
        String B = sc.nextLine();
        stringToNo(B);

        System.out.print("dist = ");
        dist(A, B);

        System.out.print("C = ");
        String C = sc.nextLine();
        stringToNo(C);


        String strNums = sc.nextLine();
        String[] strs = strNums.split(", ");
        int[] nums = new int[strs.length];
        int ni = 0;
        for (String str : strs) {
            nums[ni++] = Integer.valueOf(str);
        }
        String D = noToString(nums);
        System.out.print("d = " + D);
    }
}
