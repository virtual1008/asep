package com.asep.repositorymgmt.analyzer.dto;

public class MethodInfo {
    private final String className;
    private final String methodName;
    private final String returnType;

    public MethodInfo(String className , String methodName , String returnType){
        this.className = className;
        this.methodName = methodName;
        this.returnType = returnType;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getReturnType() {
        return returnType;
    }
}
