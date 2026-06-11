package com.asep.repositorymgmt.analyzer.scanner;

import java.io.File;
import java.util.List;

public interface EntryPointScanner {
    List<String> scan(File repositoryRoot);
}
