package com.asep.repositorymgmt.analyzer.rules;

public class ArchitectureViolation {
    private final String type;
    private final String source;
    private final String target;
    private final String message;

    public ArchitectureViolation(String type ,
                                 String source,
                                 String target,
                                 String message){
        this.type = type;
        this.source = source;
        this.target = target;
        this.message = message;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
