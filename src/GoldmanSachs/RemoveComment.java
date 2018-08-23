package GoldmanSachs;

import java.util.ArrayList;
import java.util.List;

public class RemoveComment {
    public List<String> removeComments(String[] source) {

        boolean isBlockComment = false;
        char[] line = null;
        StringBuilder curSection = new StringBuilder();
        List<String> result = new ArrayList<>();

        for (String src : source) {
            line = src.toCharArray();
            int i = 0;
            if (!isBlockComment) {
                curSection = new StringBuilder();
            }
            while (i < line.length) {
                if (!isBlockComment && !isBlockComment && i + 1 < line.length && line[i] == '/' && line[i + 1] == '*') {
                    isBlockComment = true;
                    i++;
                } else if (!isBlockComment && i + 1 < line.length && line[i] == '/' && line[i + 1] == '/') {
                    break;
                } else if (!isBlockComment) {
                    curSection.append(line[i]);
                } else if (isBlockComment && i + 1 < line.length && line[i] == '*' && line[i + 1] == '/') {
                    isBlockComment = false;
                    i++;
                }
                i++;
            }
            if (!isBlockComment && curSection.length() != 0)
                result.add(curSection.toString());
        }

        return result;
    }
}
