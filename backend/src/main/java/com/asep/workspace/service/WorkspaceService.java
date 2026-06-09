package com.asep.workspace.service;

import com.asep.workspace.dto.CreateWorkspaceRequest;
import com.asep.workspace.dto.WorkspaceResponse;

import java.util.List;
import java.util.UUID;

public interface WorkspaceService {
    WorkspaceResponse createWorkspace(CreateWorkspaceRequest request);
    WorkspaceResponse getWorkspace(UUID workspaceId);
    List<WorkspaceResponse> getAllWorkspaces();
}
