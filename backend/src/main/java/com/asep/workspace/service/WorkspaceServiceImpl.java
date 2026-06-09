package com.asep.workspace.service;

import com.asep.common.exception.ResourceNotFoundException;
import com.asep.workspace.dto.CreateWorkspaceRequest;
import com.asep.workspace.dto.WorkspaceResponse;
import com.asep.workspace.entity.Workspace;
import com.asep.workspace.repository.WorkspaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WorkspaceServiceImpl implements WorkspaceService{


    private final WorkspaceRepository workspaceRepository;

    public WorkspaceServiceImpl(WorkspaceRepository workspaceRepository) {
        this.workspaceRepository = workspaceRepository;
    }

    @Override
    public WorkspaceResponse createWorkspace(CreateWorkspaceRequest request) {
        Workspace workspace = new Workspace();
        workspace.setName(request.getName());
        Workspace saved = workspaceRepository.save(workspace);
        return new WorkspaceResponse(
                saved.getId(),
                saved.getName()
        );
    }

    @Override
    public WorkspaceResponse getWorkspace(UUID workspaceId) {

        Workspace workspace =
                workspaceRepository.findById(workspaceId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Workspace not found"
                                ));

        return new WorkspaceResponse(
                workspace.getId(),
                workspace.getName()
        );
    }
    @Override
    public List<WorkspaceResponse> getAllWorkspaces() {

        return workspaceRepository.findAll()
                .stream()
                .map(workspace ->
                        new WorkspaceResponse(
                                workspace.getId(),
                                workspace.getName()
                        ))
                .toList();
    }
}
