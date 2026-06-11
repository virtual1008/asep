package com.asep.repositorymgmt.analyzer.parser;

import com.asep.repositorymgmt.analyzer.dto.ParsedProjectInfo;

import java.io.File;

public interface BuildFileParser {
    boolean supports(File repositoryRoot);
    ParsedProjectInfo parse(File repositoryRoot);
}
