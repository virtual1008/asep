package com.asep.repositorymgmt.entity;

import com.asep.common.entity.BaseEntity;
import com.asep.project.entity.Project;
import com.asep.repositorymgmt.enums.RepositoryStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "repository")
public class Repository extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "project_id",
            nullable = false
    )
    private Project project;

    @Column(nullable = false)
    private String name;

    @Column(name = "git_url", nullable = false)
    private String gitUrl;

    @Column(name = "default_branch")
    private String defaultBranch;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RepositoryStatus status;

    @Column(name = "local_path")
    private String localPath;

    @Column(name = "last_synced_at")
    private LocalDateTime lastSyncedAt;

    @Column(name = "sync_error")
    private String syncError;

    @Column(name = "language")
    private String language;

    @Column(name = "framework")
    private String framework;

    @Column(name = "analyzed_at")
    private LocalDateTime analyzedAt;

    @Column(name = "build_tool")
    private String buildTool;

    @Column(name = "language_version")
    private String languageVersion;

    public UUID getId() {
        return id;
    }

    public String getBuildTool(){
        return buildTool;
    }

    public void setBuildTool(String buildTool) {
        this.buildTool = buildTool;
    }

    public Project getProject() {
        return project;
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

    public void setProject(Project project) {
        this.project = project;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
    }

    public void setStatus(RepositoryStatus status) {
        this.status = status;
    }

    public String getLocalPath(){
        return localPath;
    }
    public void setLocalPath(String localPath){
        this.localPath = localPath;
    }
    public LocalDateTime getLastSyncedAt(){
        return lastSyncedAt;
    }
    public void setLastSyncedAt(LocalDateTime lastSyncedAt){
        this.lastSyncedAt = lastSyncedAt;
    }
    public String getSyncError(){
        return syncError;
    }

    public void setSyncError(String syncError){
        this.syncError = syncError;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFramework() {
        return framework;
    }

    public void setFramework(String framework) {
        this.framework = framework;
    }

    public LocalDateTime getAnalyzedAt() {
        return analyzedAt;
    }

    public void setAnalyzedAt(LocalDateTime analyzedAt) {
        this.analyzedAt = analyzedAt;
    }

    public String getLanguageVersion() {
        return languageVersion;
    }

    public void setLanguageVersion(String languageVersion) {
        this.languageVersion = languageVersion;
    }
}