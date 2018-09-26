package CompanyOriented.Facebook;

public class RomanToInteger {

    public static void main(String[] args) {
        RomanToInteger inst = new RomanToInteger();
        inst.romanToInt("III");
    }

    int[] charToInt;

    public int romanToInt(String s) {

        charToInt = new int[128];
        buildCharToInt();
        char[] array = s.toCharArray();
        int result = 0, i = 0, curInt = 0, nextInt = 0;

        while (i + 1 < array.length) {
            curInt = charToInt[array[i]];
            nextInt = charToInt[array[i + 1]];
            if (curInt >= nextInt) {
                result += curInt;
                i++;
            } else {
                result += nextInt - curInt;
                i += 2;
            }
        }

        if (i < array.length) {
            result += charToInt[array[i]];
        }

        return result;
    }

    private void buildCharToInt() {
        charToInt['I'] = 1;
        charToInt['V'] = 5;
        charToInt['X'] = 10;
        charToInt['L'] = 50;
        charToInt['C'] = 100;
        charToInt['D'] = 500;
        charToInt['M'] = 1000;
    }
}
