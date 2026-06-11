package com.asep.workspace.dto;

import java.util.UUID;

public class WorkspaceResponse {
    private UUID id;
    private String name;

    public WorkspaceResponse(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
