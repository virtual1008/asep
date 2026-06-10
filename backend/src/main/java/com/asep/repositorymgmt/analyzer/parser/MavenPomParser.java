package com.asep.repositorymgmt.analyzer.parser;


import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

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

}
