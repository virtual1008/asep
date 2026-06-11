package com.asep.repositorymgmt.analyzer.detector;

import com.asep.repositorymgmt.analyzer.dto.DetectionResult;

import java.io.File;

public interface TechnologyDetector {
    boolean supports(File repositoryRoot);

    DetectionResult detect(File repositoryRoot);
}
