package com.asep.project.dto;

import java.util.UUID;

public class ProjectResponse {

    private UUID id;
    private UUID workspaceId;
    private String name;
    private String description;

    public ProjectResponse(UUID id, UUID workspaceId, String name, String description) {
        this.id = id;
        this.workspaceId = workspaceId;
        this.name = name;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public UUID getWorkspaceId() {
        return workspaceId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}