package CompanyOriented.Facebook;

import java.util.Scanner;

/*
Given str contains only 0 to 9, -, .
When I do with complicated string manipulation, I always think in a way:
+ there are 3 types of distinct section
    number_section
    -
    .
+ if it is a valid number, required order should be
    (-) number_section (.number_section)
    0 or 1 -, if exist must be put at the first position and following number_section not starts at 0
    0 or 1 ., if exist must not be put at the first postion or last postion and following number_section
 */
public class IsValidNumber {

    public static void main(String[] args) {

        IsValidNumber inst = new IsValidNumber();
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            System.out.print("input : ");
            input = scanner.nextLine();
            if (input.equals("N")) {
                break;
            }
            System.out.println(inst.isNumber(input));
        }

    }

    public boolean isNumber(String str) {

        str = str.trim();

        if (str.length() == 0) {
            return false;
        }

        if (str.length() == 1) {
            return !(str.equals("-") || str.equals(".") || str.equals("e"));
        }

        char[] array = str.toCharArray();
        int len = str.length(), i = 0;
        boolean hasDot = false, hasE = false, hasNumber = false, hasNumberAfterE = false;

        while (i < len) {
            /*
            0;
            1

            if (+ /- ) { // ++
              i++;
            }
            while ( ) {
             i++;
            }
            if ( .) { // .3
            i++;
             while ()
            }

+/- i;
1  num
.
e
+/-  (num) + (. num)
i
i++
while (isdigit(i)) i++
if (.) i++   (i == nums.length(
while (isdigit(i)) i++

break; (i

+/- ().( e+-()  )
             */

            if (array[i] >= '0' && array[i] <= '9') {
                hasNumberAfterE = true;
                hasNumber = true;
            } else if (array[i] == '-' || array[i] == '+') {
                if (i != 0 && array[i - 1] != 'e') {
                    return false;
                }
            } else if (array[i] == '.') {
                if (hasDot || hasE)
                    return false;
                hasDot = true;
            } else if (array[i] == 'e') {
                if (hasE || !hasNumber) {
                    return false;
                }
                hasE = true;
                hasNumberAfterE = false;
            } else {
                return false;
            }
            i++;
        }

        return hasNumber && hasNumberAfterE;
    }

}
