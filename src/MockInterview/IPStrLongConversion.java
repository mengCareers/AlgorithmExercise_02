package MockInterview;

public class IPStrLongConversion {

    public static void main(String[] args) {
        long numLong = ipToLong("13.25.35.200");
        System.out.println(numLong);
        String str = longToIP(numLong);
        System.out.println(str);
    }

    public static long ipToLong(String strIP) {
        String[] ipArr = strIP.split("\\.");
        long res = 0;
        // assume valid
        for (int i = 0; i < 4; i++) {
            res = res * 1000 + Long.valueOf(ipArr[i]);
        }
        return res;
    }

    public static String longToIP(long ip) {
        StringBuilder result = new StringBuilder();
        while (ip != 0) {
            result.insert(0, "." + (ip % 1000));
            ip /= 1000;
        }
        result.deleteCharAt(0);
        return result.toString();
    }

}
