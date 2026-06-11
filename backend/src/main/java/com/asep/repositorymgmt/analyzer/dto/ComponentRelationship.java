package com.asep.repositorymgmt.analyzer.dto;

public class ComponentRelationship {
    private final String from;
    private final String to;
    public ComponentRelationship(String from , String to){
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
