package com.asep.repositorymgmt.analyzer.graph;

import com.asep.repositorymgmt.analyzer.component.ComponentInfo;
import com.asep.repositorymgmt.analyzer.dto.ArchitectureGraph;
import com.asep.repositorymgmt.analyzer.dto.ComponentRelationship;
import com.asep.repositorymgmt.analyzer.dto.GraphEdge;
import com.asep.repositorymgmt.analyzer.dto.GraphNode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArchitectureGraphBuilder {
    public ArchitectureGraph build(List<ComponentInfo> components , List<ComponentRelationship> relationships){

        List<GraphNode> nodes = new ArrayList<>();
        List<GraphEdge> edges = new ArrayList<>();

        for(ComponentInfo component:components){
            nodes.add(
                    new GraphNode(
                            component.getName(),
                            component.getName(),
                            component.getType().name()
                    )
            );
        }
        for (ComponentRelationship relationship : relationships) {

            edges.add(
                    new GraphEdge(
                            relationship.getFrom(),
                            relationship.getTo()
                    )
            );
        }
        return new ArchitectureGraph(
                nodes,
                edges
        );
    }
}
