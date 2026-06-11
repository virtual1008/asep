package com.asep.repositorymgmt.analyzer;

import com.asep.repositorymgmt.analyzer.dto.CircularDependencyViolation;
import com.asep.repositorymgmt.analyzer.dto.RepositoryGraphResponse;
import org.springframework.stereotype.Service;
import com.asep.repositorymgmt.analyzer.dto.GraphNode;
import com.asep.repositorymgmt.analyzer.dto.GraphEdge;
import java.util.*;
@Service
public class CircularDependencyDetector {
    public List<CircularDependencyViolation> detect(
            RepositoryGraphResponse graph) {

        Map<String, List<String>> adjacency = new HashMap<>();

        for (GraphNode node : graph.getNodes()) {
            adjacency.put(node.getId(), new ArrayList<>());
        }

        for (GraphEdge edge : graph.getEdges()) {
            adjacency
                    .computeIfAbsent(edge.getSource(),
                            k -> new ArrayList<>())
                    .add(edge.getTarget());
        }

        List<CircularDependencyViolation> cycles =
                new ArrayList<>();

        Set<String> visited = new HashSet<>();
        Set<String> recursionStack = new HashSet<>();

        for (String node : adjacency.keySet()) {

            dfs(
                    node,
                    adjacency,
                    visited,
                    recursionStack,
                    new ArrayList<>(),
                    cycles
            );
        }

        return cycles;
    }

    private void dfs(
            String current,
            Map<String, List<String>> adjacency,
            Set<String> visited,
            Set<String> recursionStack,
            List<String> path,
            List<CircularDependencyViolation> cycles) {

        if (recursionStack.contains(current)) {

            int start = path.indexOf(current);

            if (start >= 0) {

                cycles.add(
                        new CircularDependencyViolation(
                                path.subList(
                                        start,
                                        path.size()
                                )
                        )
                );
            }

            return;
        }

        if (visited.contains(current)) {
            return;
        }

        visited.add(current);
        recursionStack.add(current);

        path.add(current);

        for (String next :
                adjacency.getOrDefault(
                        current,
                        Collections.emptyList())) {

            dfs(
                    next,
                    adjacency,
                    visited,
                    recursionStack,
                    path,
                    cycles
            );
        }

        recursionStack.remove(current);

        path.remove(path.size() - 1);
    }

}
