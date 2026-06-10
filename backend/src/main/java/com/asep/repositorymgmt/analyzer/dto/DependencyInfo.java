package com.asep.repositorymgmt.analyzer.dto;

public class DependencyInfo {
    private final String name;
    private final String version;
    public DependencyInfo(String name , String version){
        this.name = name;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }
}
