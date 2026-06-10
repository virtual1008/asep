package com.asep.repositorymgmt.service;

import com.asep.common.exception.ResourceNotFoundException;
import com.asep.config.RepositoryProperties;
import com.asep.repositorymgmt.entity.Repository;
import com.asep.repositorymgmt.enums.RepositoryStatus;
import com.asep.repositorymgmt.repository.CodeRepository;
import org.eclipse.jgit.api.Git;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class GitSyncServiceImpl implements GitSyncService{

    private final CodeRepository repositoryRepository;
    private final RepositoryProperties repositoryProperties;

    public GitSyncServiceImpl(CodeRepository repositoryRepository, RepositoryProperties repositoryProperties) {
        this.repositoryRepository = repositoryRepository;
        this.repositoryProperties = repositoryProperties;
    }
    @Override
    public void syncRepository(UUID repositoryId) {
        //System.out.println("Sync requested for repository: " + repositoryId);
        Repository repository = repositoryRepository.findById(repositoryId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Repository not found"));

        try{
           repository.setStatus(RepositoryStatus.CLONING);
           repositoryRepository.save(repository);
           cloneRepository(repositoryId);
        }catch (Exception ex){
            repository.setStatus(RepositoryStatus.FAILED);
            repository.setSyncError(ex.getMessage());
            repositoryRepository.save(repository);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void cloneRepository(UUID repositoryId) {

        Repository repository = repositoryRepository.findById(repositoryId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Repository not found"));

        String localPath =
                Paths.get(
                        repositoryProperties.getBasePath(),
                        repository.getId().toString()
                ).toString();
        File targetDirectory = new File(localPath);

        try {

            Git.cloneRepository()
                    .setURI(repository.getGitUrl())
                    .setDirectory(targetDirectory)
                    .call();

            repository.setLocalPath(localPath);
            repository.setLastSyncedAt(LocalDateTime.now());
            repository.setSyncError(null);
            repository.setStatus(RepositoryStatus.CLONED);
            repositoryRepository.save(repository);
        } catch (Exception ex) {
            repository.setStatus(RepositoryStatus.FAILED);
            repository.setSyncError(ex.getMessage());
            repositoryRepository.save(repository);
            throw new RuntimeException(ex);
        }
    }
}
