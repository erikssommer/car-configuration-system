package org.semesteroppgave.models.filehandlers.fileOpen;

import org.junit.jupiter.api.Test;
import org.semesteroppgave.ApplicationData;
import org.semesteroppgave.models.data.RegisterComponent;
import org.semesteroppgave.models.data.carcomponents.Motor;
import org.semesteroppgave.models.filehandlers.fileSave.FileSaver;
import org.semesteroppgave.models.filehandlers.fileSave.FileSaverJobj;

import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class FileOpenerJobjTest {

    @Test
    void open() {
        FileOpener fileOpener = new FileOpenerJobj();
        try {
            fileOpener.open(Paths.get("files/testFiles/testOpenJobj.jobj"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}