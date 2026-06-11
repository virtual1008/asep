package com.asep.repositorymgmt.analyzer.builder;

import com.asep.repositorymgmt.analyzer.component.ComponentInfo;
import com.asep.repositorymgmt.analyzer.dto.ComponentRelationship;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Component
public class ComponentRelationshipBuilder {
    public List<ComponentRelationship> build(File repositoryRoot, List<ComponentInfo> components) {
        List<ComponentRelationship> relationships = new ArrayList<>();

        for(ComponentInfo sourceNode:components){
            try{
                File sourceFile = new File(repositoryRoot, sourceNode.getFilePath());
                String content = Files.readString(sourceFile.toPath());
                for(ComponentInfo targetNode:components){
                    if(sourceNode.getName().equals(targetNode.getName())) continue;

                    if(content.contains(targetNode.getName())){
                        relationships.add(new ComponentRelationship(sourceNode.getName(), targetNode.getName()));
                    }

                }
            }catch (Exception ex){
                throw new RuntimeException("Failed to analyze relationships for " + sourceNode.getName(), ex);
            }
        }
        return relationships;
    }
}
