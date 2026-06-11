package com.asep.repositorymgmt.analyzer.detector;

import com.asep.repositorymgmt.analyzer.dto.DetectionResult;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class NodeDetector implements  TechnologyDetector{
    @Override
    public boolean supports(File repositoryRoot) {
        return new File(repositoryRoot , "package.json").exists();
    }

    @Override
    public DetectionResult detect(File repositoryRoot) {
        return new DetectionResult("JAVASCRIPT","UNKNOWN","NPM","UNKNOWN", List.of());
    }
}
