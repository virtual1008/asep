package com.asep.repositorymgmt.repository;

import com.asep.repositorymgmt.entity.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CodeRepository extends JpaRepository<Repository, UUID> {

    List<Repository> findByProjectId(UUID projectId);
}