package com.asep.repositorymgmt.analyzer.dto;

public class RepositoryHealthReport {
    private int totalComponents;
    private int controllers;
    private int services;
    private int repositories;
    private int entities;

    private int totalEndpoints;

    private int violations;

    private String grade;
    private int score;
    public RepositoryHealthReport(int totalComponents,
                                  int controllers,
                                  int services,
                                  int repositories,
                                  int entities,
                                  int totalEndpoints,
                                  int violations,
                                  String grade,
                                  int score){
        this.totalComponents = totalComponents;
        this.controllers = controllers;
        this.services = services;
        this.repositories = repositories;
        this.entities = entities;
        this.totalEndpoints = totalEndpoints;
        this.violations = violations;
        this.grade = grade;
        this.score = score;
    }

    public int getControllers() {
        return controllers;
    }

    public int getEntities() {
        return entities;
    }

    public int getRepositories() {
        return repositories;
    }

    public int getScore() {
        return score;
    }

    public int getServices() {
        return services;
    }

    public int getTotalComponents() {
        return totalComponents;
    }

    public int getTotalEndpoints() {
        return totalEndpoints;
    }

    public int getViolations() {
        return violations;
    }

    public String getGrade() {
        return grade;
    }
}
