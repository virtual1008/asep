package com.asep.project.service;

import com.asep.project.dto.CreateProjectRequest;
import com.asep.project.dto.ProjectResponse;

import java.util.List;
import java.util.UUID;

public interface ProjectService {
    ProjectResponse createProject(CreateProjectRequest request);

    ProjectResponse getProject(UUID projectId);

    List<ProjectResponse> getAllProjects();

    List<ProjectResponse> getProjectsByWorkspace(UUID workspaceId);

    void deleteProject(UUID projectId);
}
