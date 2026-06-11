package com.asep.repositorymgmt.service;

import com.asep.repositorymgmt.dto.CreateRepositoryRequest;
import com.asep.repositorymgmt.dto.RepositoryResponse;

import java.util.List;
import java.util.UUID;

public interface RepositoryService {
    RepositoryResponse createRepository(CreateRepositoryRequest request);
    RepositoryResponse getRepository(UUID repositoryId);
    List<RepositoryResponse> getAllRepositories();
    List<RepositoryResponse> getRepositoriesByProject(UUID projectId);
}
