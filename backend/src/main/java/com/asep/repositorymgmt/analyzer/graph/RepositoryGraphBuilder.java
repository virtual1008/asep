package com.asep.repositorymgmt.analyzer.graph;


import com.asep.repositorymgmt.analyzer.component.ComponentInfo;
import com.asep.repositorymgmt.analyzer.dto.ComponentRelationship;
import com.asep.repositorymgmt.analyzer.dto.GraphEdge;
import com.asep.repositorymgmt.analyzer.dto.GraphNode;
import com.asep.repositorymgmt.analyzer.dto.RepositoryGraphResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RepositoryGraphBuilder {
    public RepositoryGraphResponse build(List<ComponentInfo> components, List<ComponentRelationship> relationships) {
        List<GraphNode> nodes =
                components.stream()
                        .map(component ->
                                new GraphNode(
                                        component.getName(),
                                        component.getName(),
                                        component.getType().name()
                                )
                        )
                        .collect(Collectors.toList());

        List<GraphEdge> edges =
                relationships.stream()
                        .map(rel ->
                                new GraphEdge(
                                        rel.getFrom(),
                                        rel.getTo()
                                )
                        )
                        .collect(Collectors.toList());
        return new RepositoryGraphResponse(
                nodes,
                edges
        );
    }
}
