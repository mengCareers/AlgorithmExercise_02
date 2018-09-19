package Google.PhoneInterview;

import java.util.ArrayDeque;
import java.util.Deque;

public class SimplifyPath {
    public static void main(String[] args) {
        SimplifyPath inst = new SimplifyPath();
        System.out.println(inst.simplifyPath("/..."));
    }

    public String simplifyPath(String path) {
        String[] pathSections = path.split("/");
        int len = pathSections.length, i = 0;
        Deque<String> stack = new ArrayDeque<>();
        System.out.println(len);
        while (i < len) {
            String section = pathSections[i];
            if (section.isEmpty() || section.equals(".")) {
                i++;
                continue;
            } else if (section.equals("..")) {
                stack.pop();
            } else {
                stack.push(section);
            }
            i++;
        }

        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
            result.append("/");
        }
        return result.length() == 0 ? "/" : result.reverse().toString();
    }
}
