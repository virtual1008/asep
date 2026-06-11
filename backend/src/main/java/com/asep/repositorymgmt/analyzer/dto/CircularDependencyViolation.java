package com.asep.repositorymgmt.analyzer.dto;

import java.util.List;

public class CircularDependencyViolation {
    private final List<String> components;

    public CircularDependencyViolation(List<String> components) {
        this.components = components;
    }

    public List<String> getComponents() {
        return components;
    }
}
