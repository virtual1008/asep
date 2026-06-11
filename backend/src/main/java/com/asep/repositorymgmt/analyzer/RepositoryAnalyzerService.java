package com.asep.repositorymgmt.analyzer;

import com.asep.repositorymgmt.analyzer.dto.*;

import java.util.List;
import java.util.UUID;

public interface RepositoryAnalyzerService {
    RepositoryAnalysisResult analyzeRepository(UUID repositoryId);
    RepositoryGraphResponse getGraph(UUID repositoryId);
    RepositoryHealthReport getHealthReport(UUID repositoryId);
    List<CircularDependencyViolation> detectCircularDependencies(UUID repositoryId);

    List<UnusedComponentResponse> getUnusedComponents(UUID repositoryId);
}
