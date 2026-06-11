package com.asep.repositorymgmt.analyzer.dto;

public class DependencyInfo {
    private final String groupId;
    private final String artifactId;
    private final String version;
    public DependencyInfo(String groupId ,String artifactId, String version){
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public String getGroupId() {
        return groupId;
    }
    public String getVersion() {
        return version;
    }
}
