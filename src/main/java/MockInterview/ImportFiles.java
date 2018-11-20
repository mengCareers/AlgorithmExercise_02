package MockInterview;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ImportFiles {
    List<String> getTransitivelyImportedFiles(String fileName) {

        Set<String> allFiles = new HashSet<>();
        Set<String> visited = new HashSet<>();
        dfs(getDirectlyImportedFiles(fileName), allFiles, visited);
        List<String> result = new ArrayList<>(allFiles);

        return result;
    }

    private List<String> getDirectlyImportedFiles(String fileName) {
        return null;
    }

    private void dfs(List<String> importedFiles, Set<String> allFiles, Set<String> visited) {
        if (importedFiles.isEmpty())
            return;

        for (String importedFile : importedFiles) {
            if (visited.contains(importedFile))
                continue;
            allFiles.add(importedFile);
            visited.add(importedFile);
            dfs(getDirectlyImportedFiles(importedFile), allFiles, visited);
        }
    }

}
