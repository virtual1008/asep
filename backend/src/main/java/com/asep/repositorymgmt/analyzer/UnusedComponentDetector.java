package com.asep.repositorymgmt.analyzer;

import com.asep.repositorymgmt.analyzer.dto.GraphEdge;
import com.asep.repositorymgmt.analyzer.dto.GraphNode;
import com.asep.repositorymgmt.analyzer.dto.RepositoryGraphResponse;
import com.asep.repositorymgmt.analyzer.dto.UnusedComponentResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UnusedComponentDetector {

    public List<UnusedComponentResponse> detect(
            RepositoryGraphResponse graph) {

        Map<String, Integer> incomingCount =
                new HashMap<>();

        for (GraphNode node : graph.getNodes()) {
            incomingCount.put(node.getId(), 0);
        }

        for (GraphEdge edge : graph.getEdges()) {

            incomingCount.computeIfPresent(
                    edge.getTarget(),
                    (k, v) -> v + 1
            );
        }

        List<UnusedComponentResponse> unused =
                new ArrayList<>();

        for (GraphNode node : graph.getNodes()) {

            int count =
                    incomingCount.getOrDefault(
                            node.getId(),
                            0
                    );

            if (count == 0 &&
                    !isEntryPoint(node)) {

                unused.add(
                        new UnusedComponentResponse(
                                node.getLabel(),
                                node.getType()
                        )
                );
            }
        }

        return unused;
    }

    private boolean isEntryPoint(
            GraphNode node) {

        return "CONTROLLER".equals(node.getType())
                || "CONFIGURATION".equals(node.getType());
    }
}
