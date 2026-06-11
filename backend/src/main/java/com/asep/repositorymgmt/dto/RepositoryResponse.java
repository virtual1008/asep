package com.asep.repositorymgmt.dto;

import com.asep.repositorymgmt.enums.RepositoryStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class RepositoryResponse {

    private UUID id;
    private UUID projectId;
    private String name;
    private String gitUrl;
    private String defaultBranch;
    private RepositoryStatus status;
    private String localPath;
    private LocalDateTime lastSyncedAt;
    private String syncError;
    private String language;
    private String framework;
    private LocalDateTime analyzedAt;
    private String buildTool;

    public RepositoryResponse(
            UUID id,
            UUID projectId,
            String name,
            String gitUrl,
            String defaultBranch,
            RepositoryStatus status,
            String localPath,
            LocalDateTime lastSyncedAt,
            String syncError,
            String language,
            String framework,
            LocalDateTime analyzedAt,
            String buildTool
    ) {
        this.id = id;
        this.projectId = projectId;
        this.name = name;
        this.gitUrl = gitUrl;
        this.defaultBranch = defaultBranch;
        this.status = status;
        this.localPath = localPath;
        this.lastSyncedAt = lastSyncedAt;
        this.syncError = syncError;
        this.language = language;
        this.framework = framework;
        this.analyzedAt = analyzedAt;
        this.buildTool = buildTool;
    }

    public UUID getId() {
        return id;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public String getName() {
        return name;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public String getDefaultBranch() {
        return defaultBranch;
    }

    public RepositoryStatus getStatus() {
        return status;
    }

    public String getLocalPath(){
        return localPath;
    }

    public LocalDateTime getLastSyncedAt(){
        return lastSyncedAt;
    }

    public String getSyncError(){
        return syncError;
    }
    public String getLanguage(){
        return language;
    }
    public String getFramework(){
        return framework;
    }
    public LocalDateTime getAnalyzedAt(){
        return analyzedAt;
    }
    public String getBuildTool(){
        return buildTool;
    }
}