package com.asep.repositorymgmt.analyzer.dto;

public class UnusedComponentResponse {
    private final String component;
    private final String type;

    public UnusedComponentResponse(String component , String type){
        this.component = component;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getComponent() {
        return component;
    }
}
