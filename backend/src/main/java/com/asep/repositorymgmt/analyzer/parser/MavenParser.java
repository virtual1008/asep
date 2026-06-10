package com.asep.repositorymgmt.analyzer.parser;

import com.asep.repositorymgmt.analyzer.dto.ParsedProjectInfo;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class MavenParser implements BuildFileParser{
    @Override
    public boolean supports(File repositoryRoot) {
        return new File(
                repositoryRoot,
                "pom.xml"
        ).exists();
    }

    @Override
    public ParsedProjectInfo parse(File repositoryRoot) {
        return new ParsedProjectInfo(
                "JAVA",
                "UNKNOWN",
                "MAVEN",
                List.of()
        );
    }
}
