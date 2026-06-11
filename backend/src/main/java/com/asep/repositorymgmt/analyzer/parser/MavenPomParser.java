package com.asep.repositorymgmt.analyzer.parser;


import com.asep.repositorymgmt.analyzer.dto.DependencyInfo;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class MavenPomParser {
    public String detectJavaVersion(File pomFile) {

        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(pomFile);
            NodeList javaVersions = document.getElementsByTagName("java.version");
            if(javaVersions.getLength()>0){
                return javaVersions
                        .item(0)
                        .getTextContent();
            }
            return "UNKNOWN";
        }catch (Exception ex){
            throw new RuntimeException(
                    "Failed to parse pom.xml",
                    ex
            );
        }
    }

    public String detectFramework(File pomFile){
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(pomFile);
            NodeList artifactIds = document.getElementsByTagName("artifactId");
            for (int i = 0; i < artifactIds.getLength(); i++) {
                String artifact = artifactIds.item(i).getTextContent();
                if (artifact.contains("spring-boot")) {
                    return "SPRING_BOOT";
                }
            }
            return "UNKNOWN";
        }catch (Exception ex){
            throw new RuntimeException(
                    "Failed to parse pom.xml",
                    ex
            );
        }
    }

    public List<DependencyInfo> extractDependencies(File pomFile){
        try{
            List<DependencyInfo> dependencies = new ArrayList<>();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(pomFile);
            NodeList dependencyNodes = document.getElementsByTagName("dependency");
            for (int i = 0; i < dependencyNodes.getLength(); i++) {
                Node node = dependencyNodes.item(i);
                if (node.getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                Element dependency = (Element) node;
                String groupId = getTagValue(dependency, "groupId");
                String artifactId = getTagValue(dependency, "artifactId");
                String version = getTagValue(dependency, "version");
                dependencies.add(new DependencyInfo(groupId, artifactId, version)
                );
            }

            return dependencies;
        }catch (Exception ex){
            throw new RuntimeException("Failed to extract dependencies from pom.xml",
                    ex
            );
        }
    }

    private String getTagValue(Element element, String tagName) {
        NodeList nodes = element.getElementsByTagName(tagName);
        if (nodes.getLength() == 0) {
            return null;
        }
        return nodes
                .item(0)
                .getTextContent();
    }

}
