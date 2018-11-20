package BruteForce;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddress {
    public List<String> restoreIpAddresses(String s) {

        List<String> result = new ArrayList<>();

        if (s == null || s.isEmpty() || s.length() < 4) {
            return result;
        }

        restoreIpAddressesFrom(s, 0, result, "", 0);
        return result;
    }

    private void restoreIpAddressesFrom(String s, int index, List<String> result, String tmp, int partition) {

        if (index == s.length()) {
            if (partition == 4)
                result.add(tmp);
            return;
        }
        if (partition == 4) {
            if (index == s.length()) {
                result.add(tmp);
            }
            return;
        }
        if (index >= s.length()) {
            return;
        }

        String cut;

        cut = s.substring(index, index + 1);
        if (isValidCut(cut))
            restoreIpAddressesFrom(s, index + 1, result, (tmp.isEmpty() ? tmp + cut : tmp + "." + cut), partition + 1);


        if (index + 2 <= s.length()) {
            cut = s.substring(index, index + 2);
            if (isValidCut(cut))
                restoreIpAddressesFrom(s, index + 2, result, (tmp.isEmpty() ? tmp + cut : tmp + "." + cut), partition + 1);
        }
        if (index + 3 <= s.length()) {
            cut = s.substring(index, index + 3);
            if (isValidCut(cut)) {
                restoreIpAddressesFrom(s, index + 3, result, (tmp.isEmpty() ? tmp + cut : tmp + "." + cut), partition + 1);
            }
        }

    }

    private boolean isValidCut(String cut) {
        return Integer.parseInt(cut) <= 255 && (cut.charAt(0) != '0' || cut.length() <= 1);
    }
}
