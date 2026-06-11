package com.asep.repositorymgmt.analyzer;

import com.asep.config.RepositoryProperties;
import com.asep.repositorymgmt.analyzer.builder.ComponentRelationshipBuilder;
import com.asep.repositorymgmt.analyzer.component.ComponentInfo;
import com.asep.repositorymgmt.analyzer.component.ComponentScanner;
import com.asep.repositorymgmt.analyzer.detector.TechnologyDetector;
import com.asep.repositorymgmt.analyzer.dto.*;
import com.asep.repositorymgmt.analyzer.graph.RepositoryGraphBuilder;
import com.asep.repositorymgmt.analyzer.rules.ArchitectureRuleAnalyzer;
import com.asep.repositorymgmt.analyzer.rules.ArchitectureViolation;
import com.asep.repositorymgmt.analyzer.scanner.EntryPointScanner;
import com.asep.repositorymgmt.analyzer.scanner.RestEndpointScanner;
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
    private final EntryPointScanner entryPointScanner;
    private final ComponentScanner componentScanner;
    private final ComponentRelationshipBuilder relationshipBuilder;

    private final RestEndpointScanner restEndpointScanner;
    private final RepositoryGraphBuilder repositoryGraphBuilder;
    private final ArchitectureRuleAnalyzer architectureRuleAnalyzer;
    private final RepositoryHealthService repositoryHealthService;

    private final CircularDependencyDetector circularDependencyDetector;
    private final UnusedComponentDetector unusedComponentDetector;
    public RepositoryAnalyzerServiceImpl(
            RepositoryProperties repositoryProperties,
            List<TechnologyDetector> detectors,
            CodeRepository repositoryRepository,
            EntryPointScanner entryPointScanner,
            ComponentScanner componentScanner,
            ComponentRelationshipBuilder relationshipBuilder,
            RestEndpointScanner restEndpointScanner,
            RepositoryGraphBuilder repositoryGraphBuilder,
            ArchitectureRuleAnalyzer architectureRuleAnalyzer,
            RepositoryHealthService repositoryHealthService,
            CircularDependencyDetector circularDependencyDetector,
            UnusedComponentDetector unusedComponentDetector
    ) {
        this.repositoryProperties = repositoryProperties;
        this.detectors = detectors;
        this.repositoryRepository = repositoryRepository;
        this.entryPointScanner = entryPointScanner;
        this.componentScanner = componentScanner;
        this.relationshipBuilder = relationshipBuilder;
        this.restEndpointScanner = restEndpointScanner;
        this.repositoryGraphBuilder = repositoryGraphBuilder;
        this.architectureRuleAnalyzer = architectureRuleAnalyzer;
        this.repositoryHealthService = repositoryHealthService;
        this.circularDependencyDetector = circularDependencyDetector;
        this.unusedComponentDetector = unusedComponentDetector;
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
                List<String> entryPoints = entryPointScanner.scan(repositoryDirectory);
                List<ComponentInfo> components = componentScanner.scan(repositoryDirectory);
                List<ComponentRelationship> relationships = relationshipBuilder.build(repositoryDirectory, components);
                List<EndpointInfo> endpoints = restEndpointScanner.scan(repositoryDirectory, components);
                List<ArchitectureViolation> violations =
                        architectureRuleAnalyzer.analyze(
                                components,
                                relationships
                        );
                repository.setLanguage(result.getLanguage());
                repository.setFramework(result.getFramework());
                repository.setAnalyzedAt(LocalDateTime.now());
                repository.setBuildTool(result.getBuildTool());
                repository.setLanguageVersion(result.getRuntimeVersion());
                repositoryRepository.save(repository);
                return new RepositoryAnalysisResult(
                        result.getLanguage(),
                        result.getFramework(),
                        result.getBuildTool(),
                        List.of(),
                        entryPoints,
                        result.getRuntimeVersion(),
                        result.getDependencies(),
                        components,
                        relationships,
                        endpoints,
                        violations
                );
            }
        }
        repository.setLanguage("UNKNOWN");
        repository.setFramework("UNKNOWN");
        repository.setBuildTool("UNKNOWN");
        repository.setAnalyzedAt(LocalDateTime.now());
        repository.setLanguageVersion("UNKNOWN");
        repositoryRepository.save(repository);
        return new RepositoryAnalysisResult(
                "UNKNOWN",
                "UNKNOWN",
                "UNKNOWN",
                List.of(),
                List.of(),
                "UNKNOWN",
                List.of(),
                List.of(),
                List.of(),
                List.of(),
                List.of()
        );
    }

    @Override
    public RepositoryGraphResponse getGraph(UUID repositoryId) {
        RepositoryAnalysisResult analysis =
                analyzeRepository(repositoryId);

        return repositoryGraphBuilder.build(
                analysis.getComponents(),
                analysis.getRelationships()
        );
    }

    @Override
    public RepositoryHealthReport getHealthReport(UUID repositoryId) {
        RepositoryAnalysisResult analysisResult =
                analyzeRepository(repositoryId);

        RepositoryGraphResponse graph =
                getGraph(repositoryId);

        return repositoryHealthService.generate(
                analysisResult,
                graph
        );
    }

    @Override
    public List<CircularDependencyViolation> detectCircularDependencies(UUID repositoryId) {
        RepositoryGraphResponse graph = getGraph(repositoryId);
        return circularDependencyDetector.detect(graph);
    }

    @Override
    public List<UnusedComponentResponse> getUnusedComponents(UUID repositoryId) {

        RepositoryGraphResponse graph =
                getGraph(repositoryId);

        return unusedComponentDetector.detect(graph);
    }
}