package com.asep.repositorymgmt.analyzer.dto;

public class EndpointInfo {
    private final String httpMethod;
    private final String path;
    private final String controllerName;

    public EndpointInfo(String httpMethod, String path, String controllerName) {
        this.httpMethod = httpMethod;
        this.path = path;
        this.controllerName = controllerName;
    }

    public String getHttpMethod() {
        return httpMethod;
    }
    public String getPath() {
        return path;
    }
    public String getControllerName() {
        return controllerName;
    }
}
