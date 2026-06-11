package com.asep.repositorymgmt.analyzer.scanner;

import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Component
public class JavaEntryPointScanner implements EntryPointScanner {
    @Override
    public List<String> scan(File repositoryRoot) {
        List<String> entryPoints = new ArrayList<>();
        scanDirectory(repositoryRoot, repositoryRoot, entryPoints);
        return entryPoints;
    }

    private void scanDirectory(File repositoryRoot, File currentDirectory, List<String> entryPoints) {
        File[] files = currentDirectory.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                scanDirectory(repositoryRoot, file, entryPoints);
                continue;
            }
            if (!file.getName().endsWith(".java")) {
                continue;
            }
            try {
                String content = Files.readString(file.toPath());
                if (content.contains("@SpringBootApplication") || content.contains("public static void main(")) {
                    String relativePath = repositoryRoot.toPath().relativize(file.toPath()).toString();
                    String path = file.getAbsolutePath();
                    if(path.contains(File.separator + "src" + File.separator + "test" + File.separator))
                    {
                        continue;
                    }
                    entryPoints.add(relativePath);
                }
            } catch (Exception ex) {
                throw new RuntimeException(
                        "Failed to scan file: "
                                + file.getAbsolutePath(),
                        ex
                );
            }
        }
    }
}