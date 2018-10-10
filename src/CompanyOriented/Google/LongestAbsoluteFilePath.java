package CompanyOriented.Google;

/**
 * input: path
 * output:longest absolute path
 * change to List<String> if \n, add
 * keep length
 * int depth = split.lastIndexOf("\t") + 1;
 */

public class LongestAbsoluteFilePath {
    public int lengthLongestPath(String input) {
        if (!input.contains(".")) {
            return 0;
        }
        int maxVal = 0;
        String[] splits = input.split("\n");
        String[] strFrom = new String[splits.length];
        for (String split : splits) {
            int curStart = split.lastIndexOf("\t") + 1; // 0 initially
            String curStr = split.substring(curStart);
            if (curStr.contains(".")) {
                String pathAbs = "";
                for (int i = 0; i < curStart; i++) {
                    pathAbs += strFrom[i];
                }
                pathAbs += curStr;
                maxVal = Math.max(maxVal, pathAbs.length());
            } else {
                strFrom[curStart] = curStr + "/";
            }
        }
        return maxVal;
    }
}
