package CompanyOriented.Amazon;

import java.util.Scanner;

public class Util转换 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("input string : ");
        String input = scanner.nextLine();
        stringToNo(input);
        while (true) {

            if (scanner.nextLine().equals("N")) {
                break;
            } else {
                System.out.print("input string : ");
                input = scanner.nextLine();
                stringToNo(input);
            }
        }
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

}
