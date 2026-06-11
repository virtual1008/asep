package com.asep.repositorymgmt.analyzer.component;

import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
public class SpringComponentScanner implements ComponentScanner {

    @Override
    public List<ComponentInfo> scan(File repositoryRoot) {

        List<ComponentInfo> components = new ArrayList<>();

        try (Stream<java.nio.file.Path> paths =
                     Files.walk(repositoryRoot.toPath())) {

            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".java"))
                    .forEach(path -> {

                        try {

                            String absolutePath =
                                    path.toFile().getAbsolutePath();

                            if (absolutePath.contains(
                                    File.separator +
                                            "src" +
                                            File.separator +
                                            "test" +
                                            File.separator)) {
                                return;
                            }

                            String content =
                                    Files.readString(path);

                            ComponentType componentType =
                                    detectComponentType(content);

                            if (componentType == null) {
                                return;
                            }

                            String className =
                                    path.getFileName()
                                            .toString()
                                            .replace(".java", "");

                            String relativePath =
                                    repositoryRoot.toPath()
                                            .relativize(path)
                                            .toString();

                            components.add(
                                    new ComponentInfo(
                                            className,
                                            componentType,
                                            relativePath
                                    )
                            );

                        } catch (Exception ignored) {
                        }
                    });

        } catch (Exception ex) {
            throw new RuntimeException(
                    "Failed to scan Spring components",
                    ex
            );
        }

        return components;
    }

    private ComponentType detectComponentType(String content) {

        if(content.contains("@Entity")) {
            return ComponentType.ENTITY;
        }
        if (content.contains("@RestController")) {
            return ComponentType.CONTROLLER;
        }

        if (content.contains("@Controller")) {
            return ComponentType.CONTROLLER;
        }

        if (content.contains("@Service")) {
            return ComponentType.SERVICE;
        }

//        if (content.contains("@Repository")) {
//            return ComponentType.REPOSITORY;
//        }
        if (content.contains("@Repository") ||
                content.contains("extends JpaRepository") ||
                content.contains("extends CrudRepository") ||
                content.contains("extends PagingAndSortingRepository")) {
            return ComponentType.REPOSITORY;
        }

        if (content.contains("@Configuration")) {
            return ComponentType.CONFIGURATION;
        }

        if (content.contains("@Component")) {
            return ComponentType.COMPONENT;
        }

        return null;
    }
}