package com.asep.repositorymgmt.analyzer.dto;

import java.util.List;

public class RepositoryGraphResponse {
    private List<GraphNode> nodes;
    private List<GraphEdge> edges;

    public RepositoryGraphResponse(List<GraphNode> nodes,List<GraphEdge> edges){
        this.nodes = nodes;
        this.edges = edges;
    }

    public List<GraphNode> getNodes() {
        return nodes;
    }

    public List<GraphEdge> getEdges() {
        return edges;
    }
}
