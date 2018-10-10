package CompanyOriented.Google;

import java.util.Stack;

public class LicenseKeyFormatting {
    public static void main(String[] args) {
        LicenseKeyFormatting inst = new LicenseKeyFormatting();
        inst.licenseKeyFormatting("5F3Z-2e-9-w", 4);
    }

    public String licenseKeyFormatting(String S, int K) {

        char[] array = S.toCharArray();
        StringBuilder result = new StringBuilder();
        int cnt = 0;

        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] != '-') {
                if (array[i] >= 'a' && array[i] <= 'z') {
                    array[i] -= 32;
                }
                result.append(array[i]);
                cnt++;
                if (cnt == K) {
                    result.append("-");
                    cnt = 0;
                }
            }
        }

        return result.reverse().toString();
    }
}
