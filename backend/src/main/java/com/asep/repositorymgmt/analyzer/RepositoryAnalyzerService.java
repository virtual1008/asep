package com.asep.repositorymgmt.analyzer;

import com.asep.repositorymgmt.analyzer.dto.RepositoryAnalysisResult;

import java.util.UUID;

public interface RepositoryAnalyzerService {
    RepositoryAnalysisResult analyzeRepository(UUID repositoryId);
}
