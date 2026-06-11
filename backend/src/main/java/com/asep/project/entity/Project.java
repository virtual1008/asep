package com.asep.project.entity;

import com.asep.common.entity.BaseEntity;
import com.asep.workspace.entity.Workspace;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "project")
public class Project extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "workspace_id",
            nullable = false
    )
    private Workspace workspace;

    public Project() {
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Workspace getWorkspace() {
        return workspace;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }
}
