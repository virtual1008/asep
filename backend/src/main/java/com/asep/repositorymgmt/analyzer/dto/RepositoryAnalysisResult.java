package com.asep.repositorymgmt.analyzer.dto;

import java.util.List;

public class RepositoryAnalysisResult {
    private String language;
    private String framework;
    private String buildTool;
    private List<String> modules;
    private List<String> entryPoints;
    public RepositoryAnalysisResult(String language , String framework ,String buildTool, List<String> modules , List<String> entryPoints){
         this.language = language;
         this.framework = framework;
         this.modules = modules;
         this.entryPoints = entryPoints;
         this.buildTool = buildTool;
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
}
