package com.asep.repositorymgmt.analyzer.component;

import java.io.File;
import java.util.List;

public interface ComponentScanner {
    List<ComponentInfo> scan(File repositoryRoot);
}
