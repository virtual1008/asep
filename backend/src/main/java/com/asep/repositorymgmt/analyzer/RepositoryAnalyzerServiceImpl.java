package com.asep.repositorymgmt.analyzer;

import com.asep.config.RepositoryProperties;
import com.asep.repositorymgmt.analyzer.detector.TechnologyDetector;
import com.asep.repositorymgmt.analyzer.dto.DetectionResult;
import com.asep.repositorymgmt.analyzer.dto.RepositoryAnalysisResult;
import com.asep.repositorymgmt.entity.Repository;
import com.asep.repositorymgmt.repository.CodeRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class RepositoryAnalyzerServiceImpl implements RepositoryAnalyzerService {
    private final RepositoryProperties repositoryProperties;
    private final List<TechnologyDetector> detectors;
    private final CodeRepository repositoryRepository;
    public RepositoryAnalyzerServiceImpl(
            RepositoryProperties repositoryProperties,
            List<TechnologyDetector> detectors,
            CodeRepository repositoryRepository
    ) {
        this.repositoryProperties = repositoryProperties;
        this.detectors = detectors;
        this.repositoryRepository = repositoryRepository;
    }

    @Override
    public RepositoryAnalysisResult analyzeRepository(UUID repositoryId) {
        Repository repository =
                repositoryRepository.findById(repositoryId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Repository not found"));

        String repositoryPath =
                Paths.get(
                        repositoryProperties.getBasePath(),
                        repositoryId.toString()
                ).toString();

        File repositoryDirectory =
                new File(repositoryPath);

        if (!repositoryDirectory.exists()) {
            throw new RuntimeException(
                    "Repository not found on disk");
        }

        for (TechnologyDetector detector : detectors) {
            if (detector.supports(repositoryDirectory)) {
                DetectionResult result = detector.detect(repositoryDirectory);
                repository.setLanguage(result.getLanguage());
                repository.setFramework(result.getFramework());
                repository.setAnalyzedAt(LocalDateTime.now());
                repository.setBuildTool(result.getBuildTool());
                repositoryRepository.save(repository);
                return new RepositoryAnalysisResult(
                        result.getLanguage(),
                        result.getFramework(),
                        result.getBuildTool(),
                        List.of(),
                        List.of()
                );
            }
        }
        repository.setLanguage("UNKNOWN");
        repository.setFramework("UNKNOWN");
        repository.setBuildTool("UNKNOWN");
        repository.setAnalyzedAt(LocalDateTime.now());
        repositoryRepository.save(repository);
        return new RepositoryAnalysisResult(
                "UNKNOWN",
                "UNKNOWN",
                "UNKNOWN",
                List.of(),
                List.of()
        );
    }
}