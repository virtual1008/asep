package com.asep.repositorymgmt.analyzer.detector;

import com.asep.repositorymgmt.analyzer.dto.DetectionResult;
import com.asep.repositorymgmt.analyzer.parser.MavenPomParser;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class MavenDetector implements TechnologyDetector{
    private final MavenPomParser parser;

    public MavenDetector(MavenPomParser parser){
        this.parser = parser;
    }
    @Override
    public boolean supports(File repositoryRoot) {
        return new File(repositoryRoot , "pom.xml").exists();
    }

    @Override
    public DetectionResult detect(File repositoryRoot) {
        File pomFile = new File(repositoryRoot,"pom.xml");
        String javaVersion = parser.detectJavaVersion(pomFile);
        String framework = parser.detectFramework(pomFile);
        return new DetectionResult(
                "JAVA",
                framework,
                "MAVEN",
                javaVersion,
                List.of());
    }
}
