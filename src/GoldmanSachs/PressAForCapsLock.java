package GoldmanSachs;

import java.util.Scanner;

public class PressAForCapsLock {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String ori;
        while (true) {
            System.out.print("input: ");
            ori = scanner.nextLine();
            if (ori.equals("N")) {
                break;
            }
            System.out.println(press(ori));
        }
    }

    public static String press(String ori) {
        StringBuilder result = new StringBuilder();
        char[] array = ori.toCharArray();
        int i = 0, n = array.length;
        boolean isCapsLockOn = false;
        char ch;
        while (i < n) {
            ch = array[i];
            if (Character.isLetter(ch)) {
                if (array[i] == 'a') {
                    isCapsLockOn = !isCapsLockOn;
                } else if (isCapsLockOn && Character.isLowerCase(ch)) {
                    result.append(Character.toUpperCase(ch));
                } else {
                    result.append(ch);
                }
            } else {
                result.append(ch);
            }
            i++;
        }
        return result.toString();
    }
}
