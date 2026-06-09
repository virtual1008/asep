CREATE TABLE repository (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    project_id UUID NOT NULL,

    name VARCHAR(255) NOT NULL,

    git_url TEXT NOT NULL,

    default_branch VARCHAR(100),

    status VARCHAR(50),

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_repository_project
        FOREIGN KEY (project_id)
        REFERENCES project(id)
);