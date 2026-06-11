package com.asep.repositorymgmt.analyzer;

import com.asep.repositorymgmt.analyzer.component.ComponentInfo;
import com.asep.repositorymgmt.analyzer.dto.RepositoryAnalysisResult;
import com.asep.repositorymgmt.analyzer.dto.RepositoryGraphResponse;
import com.asep.repositorymgmt.analyzer.dto.RepositoryHealthReport;
import org.springframework.stereotype.Service;

@Service
public class RepositoryHealthService {

    private final CircularDependencyDetector circularDependencyDetector;
    private final UnusedComponentDetector unusedComponentDetector;

    public RepositoryHealthService(
            CircularDependencyDetector circularDependencyDetector,
            UnusedComponentDetector unusedComponentDetector) {

        this.circularDependencyDetector = circularDependencyDetector;
        this.unusedComponentDetector = unusedComponentDetector;
    }

    public RepositoryHealthReport generate(
            RepositoryAnalysisResult analysis,
            RepositoryGraphResponse graph) {

        int controllers = 0;
        int services = 0;
        int repositories = 0;
        int entities = 0;

        for (ComponentInfo component : analysis.getComponents()) {

            String type = component.getType().name();

            switch (type) {
                case "CONTROLLER" -> controllers++;
                case "SERVICE" -> services++;
                case "REPOSITORY" -> repositories++;
                case "ENTITY" -> entities++;
            }
        }

        int totalComponents = analysis.getComponents().size();
        int totalEndpoints = analysis.getEndpoints().size();

        int architectureViolations =
                analysis.getViolations().size();

        int circularDependencies =
                circularDependencyDetector.detect(graph).size();

        int unusedComponents =
                unusedComponentDetector.detect(graph).size();

        int score = 100;

        score -= architectureViolations * 10;
        score -= circularDependencies * 15;
        score -= unusedComponents * 2;

        score = Math.max(score, 0);

        String grade;

        if (score >= 90) {
            grade = "A";
        } else if (score >= 80) {
            grade = "B";
        } else if (score >= 70) {
            grade = "C";
        } else if (score >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }

        return new RepositoryHealthReport(
                totalComponents,
                controllers,
                services,
                repositories,
                entities,
                totalEndpoints,
                architectureViolations +
                        circularDependencies,
                grade,
                score
        );
    }
}