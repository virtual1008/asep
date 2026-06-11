package com.asep.repositorymgmt.analyzer.scanner;

import com.asep.repositorymgmt.analyzer.component.ComponentInfo;
import com.asep.repositorymgmt.analyzer.component.ComponentType;
import com.asep.repositorymgmt.analyzer.dto.EndpointInfo;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RestEndpointScanner {

    // Safely extracts strings inside quotes: "value" or 'value'
    private static final Pattern PATH_EXTRACTOR = Pattern.compile("[\"']([^\"']+)[\"']");

    public List<EndpointInfo> scan(File repositoryRoot, List<ComponentInfo> components) {
        List<EndpointInfo> endpoints = new ArrayList<>();

        for (ComponentInfo component : components) {
            // We only look inside components identified as Controllers (or RestControllers)
            if (component.getType() != ComponentType.CONTROLLER) {
                continue;
            }

            try {
                File file = new File(repositoryRoot, component.getFilePath());
                String content = Files.readString(file.toPath());

                // 1. Split the file to avoid confusing class-level and method-level mappings
                int classIndex = content.indexOf("class " + component.getName());
                if (classIndex == -1) classIndex = 0;

                String classHeader = content.substring(0, classIndex);
                String classBody = content.substring(classIndex);

                // 2. Extract base path from the top of the file
                String basePath = extractPath(classHeader, "@RequestMapping");

                // 3. Scan for EVERY type of HTTP method mapping in the class body
                String[] mappingTypes = {"Get", "Post", "Put", "Delete", "Patch", "Request"};

                for (String type : mappingTypes) {
                    String annotation = "@" + type + "Mapping";
                    int index = classBody.indexOf(annotation);

                    while (index >= 0) {
                        // Isolate the area right around the annotation to extract the path safely
                        int endOfAnnotation = classBody.indexOf("public", index);
                        if (endOfAnnotation == -1) endOfAnnotation = classBody.indexOf("private", index);
                        if (endOfAnnotation == -1) endOfAnnotation = index + 150; // Fallback bound

                        endOfAnnotation = Math.min(endOfAnnotation, classBody.length());
                        String annotationBlock = classBody.substring(index, endOfAnnotation);

                        // Extract the method-specific path (if one exists)
                        String methodPath = extractPath(annotationBlock, annotation);

                        // Merge with base path safely
                        String fullPath = cleanPath(basePath, methodPath);

                        // Format the HTTP Method name
                        String finalHttpMethod = type.equals("Request") ? "ANY" : type.toUpperCase();

                        endpoints.add(new EndpointInfo(finalHttpMethod, fullPath, component.getName()));

                        // Keep searching for more annotations of this type
                        index = classBody.indexOf(annotation, index + annotation.length());
                    }
                }

            } catch (Exception ex) {
                throw new RuntimeException("Failed to scan endpoints in: " + component.getName(), ex);
            }
        }
        return endpoints;
    }

    private String extractPath(String text, String annotationTarget) {
        int targetIndex = text.indexOf(annotationTarget);
        if (targetIndex == -1) return "";

        // Find parenthesis right after the annotation
        int openParen = text.indexOf('(', targetIndex);
        int closeParen = text.indexOf(')', targetIndex);

        // If no parenthesis exist (e.g., just "@GetMapping" with no path), return empty
        if (openParen == -1 || closeParen == -1 || openParen > text.indexOf('\n', targetIndex)) {
            return "";
        }

        String insideParens = text.substring(openParen, closeParen);
        Matcher matcher = PATH_EXTRACTOR.matcher(insideParens);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    private String cleanPath(String basePath, String methodPath) {
        // Concatenate paths and remove accidental double slashes
        String combined = basePath + (methodPath.startsWith("/") ? methodPath : "/" + methodPath);
        combined = combined.replaceAll("//+", "/");

        if (combined.isEmpty() || combined.equals("/")) {
            return "/";
        }
        // Remove trailing slash for consistency
        if (combined.endsWith("/") && combined.length() > 1) {
            return combined.substring(0, combined.length() - 1);
        }
        return combined;
    }
}