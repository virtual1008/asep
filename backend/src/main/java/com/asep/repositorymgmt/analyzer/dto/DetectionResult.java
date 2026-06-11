package com.asep.repositorymgmt.analyzer.dto;

import java.util.List;

public class DetectionResult {
    private final String language;
    private final String framework;
    private final String buildTool;
    private final String runtimeVersion;

    private final List<DependencyInfo> dependencies;
    public DetectionResult(String language , String framework , String buildTool,String runtimeVersion,List<DependencyInfo> dependencies){
        this.language = language;
        this.framework = framework;
        this.buildTool = buildTool;
        this.runtimeVersion = runtimeVersion;
        this.dependencies = dependencies;
    }
    public String getLanguage() {
        return language;
    }

    public String getFramework() {
        return framework;
    }

    public String getBuildTool() {
        return buildTool;
    }

    public List<DependencyInfo> getDependencies() {
        return dependencies;
    }

    public String getRuntimeVersion() {
        return runtimeVersion;
    }
}
