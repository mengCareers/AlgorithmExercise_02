package CompanyOriented.Karat;

import java.util.ArrayList;
import java.util.List;

public class SubstractSQLCommand {
    public static void main(String[] args) {
        String sqlCommands = "{};select\\;insert;select\\;;";
        System.out.println(splitCommands(sqlCommands));
    }

    private static List<String> splitCommands(String sqlCommands) {
        int i = 0, n = sqlCommands.length(), commandStart = 0;
        List<String> result = new ArrayList<>();
        while (i < n) {
            if (sqlCommands.charAt(i) == '{') {
                i++;
                while (i < n && sqlCommands.charAt(i) != '}') {
                    i++;
                }
                if (i == n)
                    break;
            } else if (i > 0 && sqlCommands.charAt(i) == ';' && sqlCommands.charAt(i - 1) != '\\') {
                result.add(sqlCommands.substring(commandStart, i + 1));
                commandStart = i + 1;
            }
            i++;
        }
        return result;
    }
}
