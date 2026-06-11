package com.asep.repositorymgmt.analyzer.scanner;

import org.springframework.stereotype.Component;
import com.asep.repositorymgmt.analyzer.component.ComponentInfo;
import com.asep.repositorymgmt.analyzer.component.ComponentType;
import com.asep.repositorymgmt.analyzer.dto.MethodInfo;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class ServiceMethodScanner {
    private static final Pattern METHOD_PATTERN =
            Pattern.compile(
                    "public\\s+([\\w<>]+)\\s+(\\w+)\\s*\\("
            );

    public List<MethodInfo> scan(
            File repositoryRoot,
            List<ComponentInfo> components
    ) {

        List<MethodInfo> methods = new ArrayList<>();

        for (ComponentInfo component : components) {

            if (component.getType() != ComponentType.SERVICE) {
                continue;
            }

            try {

                File file =
                        new File(
                                repositoryRoot,
                                component.getFilePath()
                        );

                String content =
                        Files.readString(file.toPath());

                Matcher matcher =
                        METHOD_PATTERN.matcher(content);

                while (matcher.find()) {

                    methods.add(
                            new MethodInfo(
                                    component.getName(),
                                    matcher.group(2),
                                    matcher.group(1)
                            )
                    );
                }

            } catch (Exception ex) {
                throw new RuntimeException(
                        "Failed to scan methods",
                        ex
                );
            }
        }

        return methods;
    }
}
