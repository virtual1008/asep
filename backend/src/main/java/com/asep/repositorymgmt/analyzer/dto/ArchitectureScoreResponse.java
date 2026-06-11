package com.asep.repositorymgmt.analyzer.dto;

public class ArchitectureScoreResponse {
    private final int score;
    private final String grade;
    private final int components;
    private final int endpoints;
    private final int violations;
    private final int circularDependencies;
    private final int unusedComponents;
    private final String summary;
    public ArchitectureScoreResponse(
            int score,
            String grade,
            int components,
            int endpoints,
            int violations,
            int circularDependencies,
            int unusedComponents,
            String summary) {

        this.score = score;
        this.grade = grade;
        this.components = components;
        this.endpoints = endpoints;
        this.violations = violations;
        this.circularDependencies = circularDependencies;
        this.unusedComponents = unusedComponents;
        this.summary = summary;
    }

    public int getScore() { return score; }
    public String getGrade() { return grade; }
    public int getComponents() { return components; }
    public int getEndpoints() { return endpoints; }
    public int getViolations() { return violations; }
    public int getCircularDependencies() { return circularDependencies; }
    public int getUnusedComponents() { return unusedComponents; }
    public String getSummary() { return summary; }
}
