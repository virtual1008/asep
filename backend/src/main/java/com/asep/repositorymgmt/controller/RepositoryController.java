package com.asep.repositorymgmt.controller;

import com.asep.common.response.ApiResponse;
import com.asep.repositorymgmt.analyzer.RepositoryAnalyzerService;
import com.asep.repositorymgmt.analyzer.dto.RepositoryAnalysisResult;
import com.asep.repositorymgmt.dto.CreateRepositoryRequest;
import com.asep.repositorymgmt.dto.RepositoryResponse;
import com.asep.repositorymgmt.service.GitSyncService;
import com.asep.repositorymgmt.service.RepositoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/repositories")
public class RepositoryController {
    private final RepositoryService repositoryService;
    private final GitSyncService gitSyncService;
    private final RepositoryAnalyzerService repositoryAnalyzerService;

    public RepositoryController(RepositoryService repositoryService,GitSyncService gitSyncService,RepositoryAnalyzerService repositoryAnalyzerService){
        this.gitSyncService = gitSyncService;
        this.repositoryService = repositoryService;
        this.repositoryAnalyzerService = repositoryAnalyzerService;
    }

    @PostMapping
    public ApiResponse<RepositoryResponse> createRepository(@Valid @RequestBody CreateRepositoryRequest request){
        return ApiResponse.success(
                repositoryService.createRepository(request),
                "Repository created successfully"
        );
    }

    @GetMapping("/{repositoryId}")
    public ApiResponse<RepositoryResponse> getRepository( @PathVariable UUID repositoryId){
        return ApiResponse.success(
                repositoryService.getRepository(repositoryId),
                "Repository fetched successfully"
        );
    }

    @GetMapping
    public ApiResponse<List<RepositoryResponse>> getAllRepositories() {
        return ApiResponse.success(
                repositoryService.getAllRepositories(),
                "Repositories fetched successfully"
        );
    }

    @GetMapping("/project/{projectId}")
    public ApiResponse<List<RepositoryResponse>> getRepositoriesByProject(@PathVariable UUID projectId) {
        return ApiResponse.success(
                repositoryService.getRepositoriesByProject(projectId),
                "Repositories fetched successfully"
        );
    }

    @PostMapping("/{repositoryId}/sync")
    public String syncRepository(@PathVariable UUID repositoryId) {
        gitSyncService.syncRepository(repositoryId);
        return "Repository sync started";
    }

    @PostMapping("/{repositoryId}/analyze")
    public ApiResponse<RepositoryAnalysisResult> analyzeRepository(@PathVariable UUID repositoryId) {
        return ApiResponse.success(
                repositoryAnalyzerService.analyzeRepository(repositoryId),
                "Repository analyzed successfully"
        );
    }
}
