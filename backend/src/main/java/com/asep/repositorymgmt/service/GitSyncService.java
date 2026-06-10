package com.asep.repositorymgmt.service;

import java.util.UUID;

public interface GitSyncService {
    void syncRepository(UUID repositoryId);
    void cloneRepository(UUID repositoryId);
}
