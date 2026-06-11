package com.asep.repositorymgmt.analyzer.dto;

import java.util.List;

public class ArchitectureGraph {
    private final List<GraphNode> nodes;
    private final List<GraphEdge> edges;
    public ArchitectureGraph(List<GraphNode> nodes,List<GraphEdge> edges){
        this.nodes = nodes;
        this.edges = edges;
    }

    public List<GraphEdge> getEdges() {
        return edges;
    }

    public List<GraphNode> getNodes() {
        return nodes;
    }
}
