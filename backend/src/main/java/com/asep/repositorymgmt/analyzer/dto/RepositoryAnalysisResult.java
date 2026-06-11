package com.asep.repositorymgmt.analyzer.dto;

import com.asep.repositorymgmt.analyzer.component.ComponentInfo;
import com.asep.repositorymgmt.analyzer.rules.ArchitectureViolation;

import java.util.List;

public class RepositoryAnalysisResult {
    private String language;
    private String framework;
    private String buildTool;
    private List<String> modules;
    private List<String> entryPoints;
    private String languageVersion;
    private List<DependencyInfo> dependencies;
    private List<ComponentInfo> components;
    private List<ComponentRelationship> relationships;
    private List<ArchitectureViolation> violations;

    private List<EndpointInfo> endpoints;
    public RepositoryAnalysisResult(String language , String framework ,String buildTool, List<String> modules , List<String> entryPoints,String languageVersion,List<DependencyInfo> dependencies,
                                    List<ComponentInfo> components,
                                    List<ComponentRelationship> relationships,
                                    List<EndpointInfo> endpoints,
                                    List<ArchitectureViolation> violations){
         this.language = language;
         this.framework = framework;
         this.modules = modules;
         this.entryPoints = entryPoints;
         this.buildTool = buildTool;
         this.languageVersion = languageVersion;
         this.dependencies = dependencies;
         this.components = components;
         this.relationships = relationships;
         this.endpoints = endpoints;
         this.violations = violations;
    }

    public List<ComponentRelationship> getRelationships() {
        return relationships;
    }
    public String getLanguage(){
        return language;
    }
    public String getFramework(){
        return framework;
    }
    public List<String> getModules(){
        return modules;
    }
    public List<String> getEntryPoints(){
        return entryPoints;
    }
    public String getBuildTool(){
        return buildTool;
    }

    public List<DependencyInfo> getDependencies() {
        return dependencies;
    }

    public String getLanguageVersion() {
        return languageVersion;
    }

    public List<ComponentInfo> getComponents() {
        return components;
    }
    public List<EndpointInfo> getEndpoints() {
        return endpoints;
    }

    public List<ArchitectureViolation> getViolations() {
        return violations;
    }
}
