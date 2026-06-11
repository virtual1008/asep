package com.asep.repositorymgmt.analyzer.rules;


import com.asep.repositorymgmt.analyzer.component.ComponentInfo;
import com.asep.repositorymgmt.analyzer.component.ComponentType;
import com.asep.repositorymgmt.analyzer.dto.ComponentRelationship;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ArchitectureRuleAnalyzer {
    public List<ArchitectureViolation> analyze(
            List<ComponentInfo> components,
            List<ComponentRelationship> relationships) {

        List<ArchitectureViolation> violations =
                new ArrayList<>();

        Map<String, ComponentType> componentMap =
                components.stream()
                        .collect(Collectors.toMap(
                                ComponentInfo::getName,
                                ComponentInfo::getType
                        ));

        for (ComponentRelationship relation : relationships) {

            ComponentType source =
                    componentMap.get(relation.getFrom());

            ComponentType target =
                    componentMap.get(relation.getTo());

            if (source == ComponentType.CONTROLLER
                    && target == ComponentType.REPOSITORY) {

                violations.add(
                        new ArchitectureViolation(
                                "LAYER_VIOLATION",
                                relation.getFrom(),
                                relation.getTo(),
                                "Controller should not directly depend on Repository"
                        )
                );
            }

            if (source == ComponentType.REPOSITORY
                    && target == ComponentType.CONTROLLER) {

                violations.add(
                        new ArchitectureViolation(
                                "LAYER_VIOLATION",
                                relation.getFrom(),
                                relation.getTo(),
                                "Repository should not depend on Controller"
                        )
                );
            }
        }

        return violations;
    }
}
