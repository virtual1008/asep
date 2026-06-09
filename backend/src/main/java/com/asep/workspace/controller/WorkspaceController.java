package com.asep.workspace.controller;

import com.asep.common.response.ApiResponse;
import com.asep.workspace.dto.CreateWorkspaceRequest;
import com.asep.workspace.dto.WorkspaceResponse;
import com.asep.workspace.service.WorkspaceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/workspaces")
public class WorkspaceController {
    private final WorkspaceService workspaceService;

    public WorkspaceController(WorkspaceService workspaceService){
        this.workspaceService = workspaceService;
    }

    @GetMapping("/health")
    public String health() {
        return "WORKING";
    }
    @GetMapping("/{workspaceId}")
    public ApiResponse<WorkspaceResponse> getWorkspace(@PathVariable UUID workspaceId) {
        return ApiResponse.success(
                workspaceService.getWorkspace(workspaceId),
                "Workspace fetched successfully"
        );
    }

    @GetMapping
    public ApiResponse<List<WorkspaceResponse>> getAllWorkspaces() {
        return ApiResponse.success(
                workspaceService.getAllWorkspaces(),
                "Workspaces fetched successfully"
        );
    }
    @PostMapping
    public ApiResponse<WorkspaceResponse> createWorkspace(@Valid @RequestBody CreateWorkspaceRequest request){
        WorkspaceResponse response = workspaceService.createWorkspace(request);
        return ApiResponse.success(
                response,
                "Workspace created successfully"
        );
    }
}
