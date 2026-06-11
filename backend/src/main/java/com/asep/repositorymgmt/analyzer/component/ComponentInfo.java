package com.asep.repositorymgmt.analyzer.component;

public class ComponentInfo {
    private String name;
    private ComponentType type;
    private String filePath;
    public ComponentInfo(String name , ComponentType type , String filePath){
        this.name = name;
        this.type = type;
        this.filePath = filePath;
    }

    public String getName() {
        return name;
    }

    public ComponentType getType() {
        return type;
    }

    public String getFilePath() {
        return filePath;
    }
}
