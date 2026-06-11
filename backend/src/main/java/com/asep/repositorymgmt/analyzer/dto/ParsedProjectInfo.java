package com.asep.repositorymgmt.analyzer.dto;

import java.util.List;

public class ParsedProjectInfo {

    private final String language;
    private final String framework;
    private final String buildTool;
    private final List<DependencyInfo> dependencies;
    public ParsedProjectInfo(String language,
                             String framework,
                             String buildTool,
                             List<DependencyInfo> dependencies){
        this.language = language;
        this.buildTool = buildTool;
        this.framework = framework;
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
}
