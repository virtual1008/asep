package com.asep.project.service;

import com.asep.common.exception.ResourceNotFoundException;
import com.asep.project.dto.CreateProjectRequest;
import com.asep.project.dto.ProjectResponse;
import com.asep.project.entity.Project;
import com.asep.project.repository.ProjectRepository;
import com.asep.workspace.entity.Workspace;
import com.asep.workspace.repository.WorkspaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final WorkspaceRepository workspaceRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, WorkspaceRepository workspaceRepository) {
        this.projectRepository = projectRepository;
        this.workspaceRepository = workspaceRepository;
    }

    @Override
    public ProjectResponse createProject(CreateProjectRequest request) {
        Workspace workspace = workspaceRepository.findById(request.getWorkspaceId())
                            .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Workspace not found"));

        Project project = new Project();

        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setWorkspace(workspace);
        Project saved = projectRepository.save(project);
        return mapToResponse(saved);
    }

    @Override
    public ProjectResponse getProject(UUID projectId) {

        Project project =
                projectRepository.findById(projectId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Project not found"));

        return mapToResponse(project);
    }

    @Override
    public List<ProjectResponse> getAllProjects() {

        return projectRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private ProjectResponse mapToResponse(Project project) {
        return new ProjectResponse(
                project.getId(),
                project.getWorkspace().getId(),
                project.getName(),
                project.getDescription()
        );
    }
    @Override
    public List<ProjectResponse> getProjectsByWorkspace(UUID workspaceId) {
        return projectRepository
                .findByWorkspaceId(workspaceId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void deleteProject(UUID projectId) {
        Project project =
                projectRepository.findById(projectId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Project not found"
                                ));

        projectRepository.delete(project);
    }
}