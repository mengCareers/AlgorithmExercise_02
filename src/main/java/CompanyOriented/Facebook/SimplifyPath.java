package CompanyOriented.Facebook;

import java.util.Stack;

public class SimplifyPath {
    public String simplifyPath(String path) {

        String[] splits = path.split("[\\/]+");
        Stack<String> stackFiles = new Stack<>();
        Stack<String> stackUtil = new Stack<>();
        StringBuilder result = new StringBuilder();

        for (String split : splits) {
            if (split.equals("..")) {
                if (!stackFiles.isEmpty()) {
                    stackFiles.pop();
                }
            } else if (!split.isEmpty() && !split.equals(".")) {
                stackFiles.push(split);
            }
        }

        while (!stackFiles.isEmpty()) {
            stackUtil.push(stackFiles.pop());
        }
        while (!stackUtil.isEmpty()) {
            result.append("/").append(stackUtil.pop());
        }

        return result.length() == 0 ? "/" : result.toString();
    }
}
