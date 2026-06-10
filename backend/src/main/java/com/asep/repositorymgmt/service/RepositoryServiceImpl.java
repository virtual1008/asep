package com.asep.repositorymgmt.service;

import com.asep.common.exception.ResourceNotFoundException;
import com.asep.project.entity.Project;
import com.asep.project.repository.ProjectRepository;
import com.asep.repositorymgmt.dto.CreateRepositoryRequest;
import com.asep.repositorymgmt.dto.RepositoryResponse;
import com.asep.repositorymgmt.entity.Repository;
import com.asep.repositorymgmt.enums.RepositoryStatus;
import com.asep.repositorymgmt.repository.CodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RepositoryServiceImpl implements RepositoryService {
    private final CodeRepository repositoryRepository;
    private final ProjectRepository projectRepository;
    public RepositoryServiceImpl(CodeRepository repositoryRepository, ProjectRepository projectRepository) {
        this.repositoryRepository = repositoryRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public RepositoryResponse createRepository(CreateRepositoryRequest request) {
        Project project =
                projectRepository.findById(
                                request.getProjectId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Project not found"));

        Repository repository = new Repository();
        repository.setProject(project);
        repository.setName(request.getName());
        repository.setGitUrl(request.getGitUrl());
        repository.setDefaultBranch(request.getDefaultBranch());
        repository.setStatus(RepositoryStatus.CREATED);
        Repository saved = repositoryRepository.save(repository);
        return mapToResponse(saved);
    }

    @Override
    public RepositoryResponse getRepository(UUID repositoryId) {
        Repository repository = repositoryRepository.findById(repositoryId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Repository not found"));

        return mapToResponse(repository);
    }

    @Override
    public List<RepositoryResponse>
    getAllRepositories() {

        return repositoryRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<RepositoryResponse> getRepositoriesByProject(UUID projectId) {
        return repositoryRepository
                .findByProjectId(projectId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private RepositoryResponse mapToResponse(Repository repository) {
        return new RepositoryResponse(
                repository.getId(),
                repository.getProject().getId(),
                repository.getName(),
                repository.getGitUrl(),
                repository.getDefaultBranch(),
                repository.getStatus(),
                repository.getLocalPath(),
                repository.getLastSyncedAt(),
                repository.getSyncError(),
                repository.getLanguage(),
                repository.getFramework(),
                repository.getAnalyzedAt(),
                repository.getBuildTool()
        );
    }
}