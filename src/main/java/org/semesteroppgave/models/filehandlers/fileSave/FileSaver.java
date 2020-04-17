package org.semesteroppgave.models.filehandlers.fileSave;

import java.io.IOException;
import java.nio.file.Path;

public interface FileSaver {
    void save(Path filePath) throws IOException;
}
