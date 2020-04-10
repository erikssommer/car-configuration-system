package org.semesteroppgave.io;

import org.semesteroppgave.RegisterComponent;

import java.io.IOException;
import java.nio.file.Path;

public interface FileSaver {
    void save(RegisterComponent registerComponent, Path filePath) throws IOException;
}
